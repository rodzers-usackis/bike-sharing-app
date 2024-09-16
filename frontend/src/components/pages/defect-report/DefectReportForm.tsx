import {useForm, SubmitHandler, Controller} from "react-hook-form";
import {
    FormControl,
    TextField,
    Checkbox,
    Button,
    FormLabel,
    FormControlLabel
} from '@mui/material';
import React, {useState} from "react";
import "./DefectReportPage.css";
import {DefectFormInputs} from "./DefectFormInputs";
import {useCreateVehicleDefectReport} from "../../../hooks/defect-report/useCreateVehicleDefectReport";
import {useNavigate} from "react-router-dom";

const defaultFormValues = {
    isUnusable: false,
    hasBrokenWheel: false,
    hasFlatTyre: false,
    hasBrokenLight: false,
    hasBrokenBrake: false,
    hasOtherDefect: false,
    photo: ''
}

export function DefectReportForm() {
    const {
        register,
        handleSubmit,
        formState: {errors},
        control,
        setValue,
        reset
    } = useForm<DefectFormInputs>({
        defaultValues: defaultFormValues
    });
    const [imageFile, setImageFile] = useState<File>();
    const {mutate: createVehicleDefectReport} = useCreateVehicleDefectReport();

    const navigate = useNavigate();

    const onDefectFormSubmit: SubmitHandler<DefectFormInputs> = (data) => {
        createVehicleDefectReport(data, {
            onSuccess: () => {
                //TODO: check if this works
                reset(defaultFormValues)
                navigate("/map");
            }
        });
    };


    // @ts-ignore
    // @ts-ignore
    // @ts-ignore
    // @ts-ignore
    // @ts-ignore
    return <FormControl fullWidth={true}>
        <>
            {/*TODO: get these values from somewhere    */}
            <TextField value="2ce1dda3-71a6-4383-bbf2-133318d90ad8" sx={{display: "none"}}
                       autoComplete={"off"} {...register("customerId", {required: true})} />
            <TextField value="36eddf39-a93e-46ac-b79b-c329f107abfe" sx={{display: "none"}}
                       autoComplete={"off"} {...register("vehicleId", {required: true})} />

            <TextField
                id="description"
                label="Problem description"
                error={!!errors.description}
                helperText={errors?.description ? errors.description.message : ""} autoComplete={"off"}
                fullWidth={true}
                multiline={true}
                minRows={4}
                sx={{
                    mb: 4
                }}
                {...register("description", {
                    required: "Description is required.",
                    minLength: {value: 10, message: "Description must be at least 10 characters long."},
                    maxLength: {value: 2500, message: "Description must be at most 2500 characters long."}
                })} />

            <FormLabel component="legend" sx={{color: "black"}}>Vehicle... </FormLabel>
            <FormControlLabel
                control={
                    <Controller
                        name="isUnusable"
                        control={control}
                        render={({field}) => <Checkbox {...field}/>
                        }
                    />}
                label="is unusable"/>
            <FormControlLabel
                control={
                    <Controller
                        name="hasFlatTyre"
                        control={control}
                        render={({field}) => <Checkbox {...field}/>
                        }
                    />}
                label="has a flat tyre"/>

            <FormControlLabel
                control={
                    <Controller
                        name="hasBrokenWheel"
                        control={control}
                        render={({field}) => <Checkbox {...field}/>
                        }
                    />}
                label="has a broken wheel"/>

            <FormControlLabel
                control={
                    <Controller
                        name="hasBrokenBrake"
                        control={control}
                        render={({field}) => <Checkbox {...field}/>
                        }
                    />}
                label="has a broken brake"/>

            <FormControlLabel
                control={
                    <Controller
                        name="hasBrokenLight"
                        control={control}
                        render={({field}) => <Checkbox {...field}/>
                        }
                    />}
                label="has a broken light"/>

            <FormControlLabel
                control={
                    <Controller
                        name="hasOtherDefect"
                        control={control}
                        render={({field}) => <Checkbox {...field}/>
                        }
                    />}
                label="has another defect"/>


            {imageFile &&
            <img className={"img-upload-thumbnail"} alt="bike with defect" src={URL.createObjectURL(imageFile)}/>}

            <input className="choose-file" type="file" name="photo" onChange={(event) => {
                if (event?.target?.files && event.target.files.length > 0) {
                    // console.log('file selected');
                    // console.log(event.target.files)
                    setImageFile(event.target.files[0] ?? undefined);
                    if (!!event.target.files[0]) {
                        // console.log('if to set b64 passed')
                        fileToBase64(event.target.files[0]).then((base64) => {
                            // console.log('b64 set', base64);
                            setValue("photo", base64);
                        })
                    }
                }

            }} accept="image/*" capture="environment"/>

            {/*{watch("photo") && console.log(getValues("photo"))}*/}

            <Button type="submit" variant="contained" onClick={handleSubmit(onDefectFormSubmit)}
                    sx={{mt: 4, marginRight: "auto", marginLeft: "auto", width: "50%"}}>Submit</Button>
        </>
    </FormControl>


}


function fileToBase64(file: File): Promise<string> {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = (event) => {
            const data = event.target?.result as string;
            resolve(data);
        };
        reader.onerror = (error) => {
            reject(error);
        };
        reader.readAsDataURL(file);
    });
}