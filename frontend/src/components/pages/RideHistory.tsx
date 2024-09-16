import * as React from 'react';
import Box from '@mui/material/Box';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Divider from '@mui/material/Divider';
import Typography from "@mui/material/Typography";
import {useRides} from "../../hooks/customer/useRides";
import {Ride} from "../../model/customer/Ride";


function renderRows(rides: Ride[]) {
    const formatter = new Intl.NumberFormat('en-EU', {
        style: 'currency',
        currency: 'EUR',
    });
    return (
        <>
            {rides.map(ride => {
                return (
                    <>
                        <ListItem component="div" disablePadding>
                            <ListItemButton>
                                <Box sx={{ width: '100%', display: 'flex', flexDirection: 'column',
                                    alignItems: 'center' , bgcolor: 'background.paper', border: 1, padding: 2 }}>
                                    <ListItemText primary={`${new Date(ride.endTime).toDateString()}`}/>
                                    <ListItemText primary={`${ride.startStation}`}/>
                                    <ListItemText primary={`|`}/>
                                    <ListItemText primary={`${ride.endStation}`}/>
                                    <ListItemText primary={`Time: ${new Date(ride.startTime).getHours()}:${new Date(ride.startTime).getMinutes()}:${new Date(ride.startTime).getSeconds()} - ${new Date(ride.endTime).getHours()}:${new Date(ride.endTime).getMinutes()}:${new Date(ride.endTime).getSeconds()}`}/>
                                    <ListItemText primary={`Activation Fee: ${formatter.format(ride.activationFee)}`}/>
                                    <ListItemText primary={`Price Per Minute: ${formatter.format(ride.pricePerMinute)}`}/>
                                    <ListItemText primary={`Total Cost: ${formatter.format(ride.totalCost)}`}/>
                                </Box>
                            </ListItemButton>
                        </ListItem>
                    </>
                )
            })}
        </>
    );
}

export default function RideHistory() {
    // hard coded customer id
    const {isLoadingRides, isErrorLoadingRides, rides} = useRides('2ce1dda3-71a6-4383-bbf2-133318d90ad8')

    if (isLoadingRides) {
        return <>
            <Typography
                component="h3"
                variant="h3"
                align="center"
                color="text.primary"
                gutterBottom
                fontWeight={500}
                padding={2}
            >
                Order History
            </Typography>
            <Typography component="h5"
                        variant="h5"
                        align="center"
                        color="text.primary"
                        gutterBottom
                        fontWeight={500}
                        padding={2}>Loading...</Typography>
        </>

    }

    if (isErrorLoadingRides) {
        return <>
            <Typography
                component="h3"
                variant="h3"
                align="center"
                color="text.primary"
                gutterBottom
                fontWeight={500}
                padding={2}
            >
                Order History
            </Typography>
            <Typography component="h5"
                        variant="h5"
                        align="center"
                        color="text.primary"
                        gutterBottom
                        fontWeight={500}
                        padding={2}>Customer has no ride history</Typography>
        </>

    }

    if (rides === undefined) {
        return <>
            <Typography
                component="h3"
                variant="h3"
                align="center"
                color="text.primary"
                gutterBottom
                fontWeight={500}
                padding={2}
            >
                Order History
            </Typography>
            <Typography component="h5"
                        variant="h5"
                        align="center"
                        color="text.primary"
                        gutterBottom
                        fontWeight={500}
                        padding={2}>Customer has no ride history</Typography>
        </>

    }


    return (
        <Box sx={{ width: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center' ,bgcolor: 'background.paper' }}>
            <Typography
                component="h3"
                variant="h3"
                align="center"
                color="text.primary"
                gutterBottom
                fontWeight={500}
                padding={2}
            >
                Order History
            </Typography>
        <Box sx={{ width: '90%', height: 400, bgcolor: 'background.paper' }}>
                <List>
                    {renderRows(rides)}
                </List>
        </Box>
        </Box>
    );
}