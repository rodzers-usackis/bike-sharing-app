import React from 'react';
import {useMediaQuery} from "@mui/material";
import Box from "@mui/material/Box";
import RideHistory from "./RideHistory";

export default function Profile() {
    const matches = useMediaQuery('(max-width:599px)');


    return (
        <>
            <RideHistory/>
        </>
    );
}
