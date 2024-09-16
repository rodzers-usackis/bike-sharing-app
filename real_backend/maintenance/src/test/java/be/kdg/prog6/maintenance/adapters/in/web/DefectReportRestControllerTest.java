package be.kdg.prog6.maintenance.adapters.in.web;

import be.kdg.prog6.maintenance.adapters.out.db.DefectReportJpaRepository;
import be.kdg.prog6.maintenance.adapters.out.db.model.DefectReportJpaEntity;
import be.kdg.prog6.maintenance.domain.VehicleDefectReportStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(locations = "classpath:application-tests.yml")
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefectReportRestControllerTest {

//    @Autowired
//    private DefectReportJpaRepository defectReportJpaRepository;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private String photo = "data:image/gif;base64,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";
//    private UUID uuidOpenReport = UUID.randomUUID();
//    private UUID uuidSolvedReport = UUID.randomUUID();
//
//    @BeforeAll
//    void setup() {
//
//        DefectReportJpaEntity defectReport = new DefectReportJpaEntity();
//        defectReport.setUuid(uuidSolvedReport);
//        defectReport.setDescription("test");
//        defectReport.setCustomerContextReportId(UUID.randomUUID());
//        defectReport.setHasBrokenBrake(true);
//        defectReport.setCustomerId(UUID.randomUUID());
//        defectReport.setPhoto(photo);
//        defectReport.setTimestamp(LocalDateTime.now());
//        defectReport.setVehicleId(UUID.randomUUID());
//        defectReport.setHasOtherDefect(true);
//        defectReport.setStatus(VehicleDefectReportStatus.SOLVED);
//        defectReportJpaRepository.save(defectReport);
//
//        DefectReportJpaEntity defectReport2 = new DefectReportJpaEntity();
//        defectReport2.setUuid(uuidOpenReport);
//        defectReport2.setDescription("test2");
//        defectReport2.setCustomerContextReportId(UUID.randomUUID());
//        defectReport2.setHasBrokenBrake(true);
//        defectReport2.setCustomerId(UUID.randomUUID());
//        defectReport2.setPhoto(photo);
//        defectReport2.setTimestamp(LocalDateTime.now());
//        defectReport2.setVehicleId(UUID.randomUUID());
//        defectReport2.setHasOtherDefect(false);
//        defectReport2.setStatus(VehicleDefectReportStatus.OPEN);
//        defectReportJpaRepository.save(defectReport2);
//
//
//    }
//
//    @Test
//    void getAllDefectReports() throws Exception {
//
//        List<DefectReportJpaEntity> reports = defectReportJpaRepository.findAll();
//
//        mockMvc.perform(get("/maintenance/api/vehicle-defect-reports")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].uuid").value(reports.get(0).getUuid().toString()))
//                .andExpect(jsonPath("$[1].uuid").value(reports.get(1).getUuid().toString()))
//                .andExpect(jsonPath("$[0].description").value(reports.get(0).getDescription()))
//                .andExpect(jsonPath("$[1].description").value(reports.get(1).getDescription()));
//    }
//
//    @Test
//    void getOpenReports() throws Exception {
//
//        mockMvc.perform(get("/maintenance/api/vehicle-defect-reports/?status=OPEN")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].uuid").value(uuidOpenReport.toString()));
//    }
//
//    @Test
//    void changeReportStatus() throws Exception {
//        mockMvc.perform(patch("/maintenance/api/vehicle-defect-report/" + uuidOpenReport.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"status\": \"SOLVED\"}"))
//                .andExpect(status().isNoContent());
//
//        assertEquals(defectReportJpaRepository.findById(uuidOpenReport).orElseThrow().getStatus(), VehicleDefectReportStatus.SOLVED);
//
//    }


}