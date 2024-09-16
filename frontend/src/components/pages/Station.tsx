import {useParams} from "react-router-dom";
import {
    Alert,
    CircularProgress,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow
} from "@mui/material";
import {useStation} from "../../hooks/stations/useStation";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import * as React from "react";
import {useBikes} from "../../hooks/stations/useBikes";
import Button from "@mui/material/Button";
import {endBikeRide, startBikeRide} from "../../services/API";
import {useState} from "react";


export default function Station() {
    const {id} = useParams();
    const {isLoadingStation, isErrorLoadingStation, station} = useStation(id!)
    // For now, I'm just getting all bikes, regardless of station.
    const {isLoadingBikes, isErrorLoadingBikes, bikes} = useBikes()
    const [isLoading, setIsLoading] = useState(false);

    if (isLoadingStation || isLoadingBikes || isLoading) {
        return <CircularProgress sx={{display: "block", mt: "10em", mx: "auto"}}/>
    }

    if (isErrorLoadingStation) {
        return <Alert severity="error">Station could not be loaded</Alert>;
    }

    if (isErrorLoadingBikes) {
        return <Alert severity="error">Bikes could not be loaded</Alert>;
    }

    if (bikes!.length == 0) {
        return <Alert severity="error">There are no bikes in the database yet, please add some.</Alert>;
    }

    function startRide(uuid: string) {
        setIsLoading(true)
        startBikeRide(uuid).then(r => console.log(r))
        setIsLoading(false)
    }

    function endRide(uuid: string) {
        setIsLoading(true)
        endBikeRide(uuid).then(r => console.log(r))
        setIsLoading(false)
    }

    return (
        <Container className={'station-container'} disableGutters maxWidth="lg" component="main" sx={{pt: 8, pb: 6}}>
            <Typography
                className={'station-title'}
                component="h3"
                variant="h3"
                align="center"
                color="text.primary"
                gutterBottom
                fontWeight={500}
            >
                {/* {station?.name} */}
            </Typography>
            <Container maxWidth={'lg'} sx={{
                display: 'flex'
            }}>
                <TableContainer component={Paper}>
                    <Table sx={{minWidth: 650}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="right">ID</TableCell>
                                <TableCell align="right">Start Date</TableCell>
                                <TableCell align="right">Last Maintenance Day</TableCell>
                                <TableCell align="right">Model</TableCell>
                                <TableCell align="right">Distance Traveled (meters)</TableCell>
                                <TableCell align="right">Vehicle State</TableCell>
                                <TableCell align="right">Price Per Minute (euro)</TableCell>
                                <TableCell align="right">Activation Cost (euro)</TableCell>
                                <TableCell align="right"></TableCell>
                                <TableCell align="right"></TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {bikes!.map((element) => (
                                <TableRow key={element.id.uuid}>
                                    <TableCell align="right">{element.id.uuid}</TableCell>
                                    <TableCell align="right">{element.startDate}</TableCell>
                                    <TableCell align="right">{element.lastMaintenanceDate}</TableCell>
                                    <TableCell align="right">{element.model}</TableCell>
                                    <TableCell align="right">{element.distanceTraveledInMeters}</TableCell>
                                    <TableCell align="right">{element.vehicleState}</TableCell>
                                    <TableCell align="right">{element.price.costPerMinute.amount}</TableCell>
                                    <TableCell align="right">{element.price.activationCost.amount}</TableCell>
                                    {/*<TableCell align="right">*/}
                                    {/*    <Button key={element.id.uuid} onClick={() => startRide(element.id.uuid)}>Start Ride</Button>*/}
                                    {/*</TableCell>*/}
                                    {/*<TableCell align="right">*/}
                                    {/*    <Button key={element.id.uuid} onClick={() => endRide(element.id.uuid)}>End Ride</Button>*/}
                                    {/*</TableCell>*/}
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Container>
        </Container>
    )
}