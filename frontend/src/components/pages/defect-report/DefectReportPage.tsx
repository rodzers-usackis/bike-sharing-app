import {DefectReportForm} from "./DefectReportForm";
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import {deepOrange} from "@mui/material/colors";
import * as React from "react";
import {Box} from "@mui/material";


export function DefectReportPage() {
    return (
        <Container component="main">
            <Typography
                component="h2"
                variant="h2"
                align="center"
                color="text.primary"
                gutterBottom
                fontWeight={500}
            >
                Vehicle defect report
            </Typography>
            <Typography variant="h4" align="center" component="p" style={{color: deepOrange[500], fontWeight: 500}}>
                Tell us what's wrong with your vehicle
            </Typography>
            <Box display="flex"
            justifyContent="center"
            sx={{p:5}}>
                <DefectReportForm/>
            </Box>
        </Container>
    )
}