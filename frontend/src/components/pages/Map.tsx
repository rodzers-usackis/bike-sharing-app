import {GoogleMap, useLoadScript, InfoWindow, MarkerF} from '@react-google-maps/api'
import React, {useContext, useState} from "react";
import {Alert, CircularProgress, Drawer, Fab, Hidden, IconButton, useMediaQuery} from "@mui/material";
import {useStations} from "../../hooks/stations/useStations";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import * as MaterialIcons from 'react-icons/md'
import {QrReader} from "react-qr-reader";
import Grid from "@mui/material/Grid";
import {startBikeRide} from "../../services/API";
import {endBikeRide} from "../../services/API";
import {getAvailableDocks} from "../../services/API";
import UserInRideContext from "../../context/UserInRideContext";

export default function Map() {
    const matches = useMediaQuery('(max-width:599px)');
    const {userInRide, setUserInRide} = useContext(UserInRideContext);

    // REST API Call for loading all stations and bikes
    const {isLoadingStations, isErrorLoadingStations, stations} = useStations()

    // Bottom Drawer
    const [stationInfoDrawerOpen, setStationInfoDrawerOpen] = React.useState(false);

    const toggleStationInfoDrawer = (newOpen: boolean) => () => {
        setStationInfoDrawerOpen(newOpen);
    };

    // QR Code Scanning
    const [qrCodeScanningDrawerOpen, setQrCodeScanningDrawerOpen] = React.useState(false)
    const [startingRideDrawerOpen, setStartingRideDrawerOpen] = React.useState(false)
    const [qrScanResult, setQrScanResult] = useState("No result");
    const [scanned, setScanned] = useState(false);
    const [stationUUID, setStationUUID] = useState("");
    const [bikeUUID, setBikeUUID] = useState("");

    const toggleUserInRide = (newValue: boolean) => () => {
        setUserInRide(newValue);
        // If the user ends their ride, call backend to end ride
        if (!newValue) {
            endBikeRide(stationUUID).then(r => {
                console.log(r);
            });
        }
    };

    const toggleQrCodeScanningDrawer = (newValue: boolean) => () => {
        setQrCodeScanningDrawerOpen(newValue);
    };

    const toggleStartingRideDrawer = (newValue: boolean) => () => {
        setStartingRideDrawerOpen(newValue);
    }

    let handleScan = (data: React.SetStateAction<any>) => {
        if (data) {
            setScanned(true);
            setQrScanResult(data);
            setStartingRideDrawerOpen(true);
            setQrCodeScanningDrawerOpen(false);

            setStationUUID(data.text);
        }
    };

    let handleScanError = (err: any) => {
        alert(err);
    };

    let startRide = () => {
        startBikeRide(stationUUID).then(r => console.log(r));
        setUserInRide(true);
        setScanned(false);
        setStartingRideDrawerOpen(false)
    }

    let closeStartingRideDrawer = () => {
        setScanned(false);
        setStartingRideDrawerOpen(false)
    }

    // Map
    const {isLoaded} = useLoadScript({
        // googleMapsApiKey: process.env.NEXT_PUBLIC_GOOGLE_MAPS_API_KEY
        // shows undefined
        googleMapsApiKey: "no"
    });

    const [infoWindowOpen, setInfoWindowOpen] = useState(false);
    const [coordinates, setCoordinates] = useState({lat: 37.335480, lng: -121.893028});
    const [dockCount, setDockCount] = useState(0);

    const showInfoWindow = (lat: number, lng: number, id: string, status: string) => {
        getAvailableDocks(id, status).then(r => {
            console.log(r.data)
            setDockCount(r.data)
        })
        setCoordinates({lat: lat, lng: lng})
        setInfoWindowOpen(true);
    };


    if (isLoadingStations || !isLoaded) {
        return (
            <div className={'map-container-div'} style={{
                height: matches ? 'calc(100vh - 112px)' : 'calc(100vh - 64px)',
                display: 'flex', justifyContent: 'center', alignItems: 'center'
            }}
            >
                <CircularProgress sx={{display: "block"}}/>
            </div>
        )
    }

    if (isErrorLoadingStations) {
        return <Alert severity="error">Markers could not be loaded</Alert>;
    }

    // TODO: Figure out how to give border-radius to bottom-drawer.
    return (
        <>
            <div className={'map-container-div'}
                 style={{height: matches ? 'calc(100vh - 112px)' : 'calc(100vh - 64px)'}}>
                <GoogleMap zoom={13} center={{lat: coordinates.lat, lng: coordinates.lng}}
                           mapContainerClassName="map-container">

                    {stations?.map(({location}) => (
                        <MarkerF position={{
                            lat: location.longitude,
                            lng: location.latitude
                        }} key={location.locationUUID.uuid}
                                 onClick={toggleStationInfoDrawer(true)}>

                        </MarkerF>
                    ))}
                    {/*{infoWindowOpen && (*/}
                    {/*    <InfoWindow*/}
                    {/*        onCloseClick={() => setInfoWindowOpen(false)}*/}
                    {/*        position={{lat: coordinates.lng, lng: coordinates.lat}}>*/}
                    {/*        <Box style={{justifyContent: '', textAlign: 'center'}}>*/}
                    {/*            <h2>Station Info</h2>*/}
                    {/*            <p>Docks Available: {dockCount}</p>*/}
                    {/*            <Button variant="outlined" style={{margin: '0 auto'}} startIcon={<MaterialIcons.MdDirections/>}*/}
                    {/*                    sx={{*/}
                    {/*                        display: 'flex',*/}
                    {/*                        justifyContent: 'start'*/}
                    {/*                    }}*/}
                    {/*            >Directions</Button>*/}
                    {/*        </Box>*/}
                    {/*    </InfoWindow>*/}
                    {/*)}*/}
                </GoogleMap>
            </div>

            <Hidden smUp={!matches}>
                <div className={'fab-button-container'} style={{
                    display: 'flex',
                    width: '100%',
                    justifyContent: 'center'
                }}>

                    {!userInRide ?
                        <Fab variant="extended" size={'medium'} color="primary"
                             aria-label="add" onClick={toggleQrCodeScanningDrawer(true)} sx={{
                            bottom: '70px',
                            display: 'flex',
                            justifyContent: 'center',
                            alignItems: 'center',
                            fontSize: 17
                        }}>
                            <MaterialIcons.MdQrCode2 size={20}/>&nbsp;Scan to ride
                        </Fab>
                        :
                        <Fab variant="extended" size={'medium'} color="error"
                             aria-label="add" onClick={toggleUserInRide(false)} sx={{
                            bottom: '70px',
                            display: 'flex',
                            justifyContent: 'center',
                            alignItems: 'center',
                            fontSize: 17
                        }}>
                            <MaterialIcons.MdCancelScheduleSend size={20}/>&nbsp;End ride
                        </Fab>
                    }
                </div>

                <Drawer anchor="bottom" open={stationInfoDrawerOpen}
                        onClose={toggleStationInfoDrawer(false)}>
                    <Box className={'bottom-drawer-box'} p={2}>
                        <Typography className={'station-info-typography'}>Station #4</Typography>
                        <hr/>
                        <Typography className={'station-info-typography'} pb={2}>Dock count: 18</Typography>
                        <Typography className={'station-info-typography'} pb={2}>Bikes available: 3</Typography>
                        <Button variant="outlined" style={{margin: '0 auto'}} startIcon={<MaterialIcons.MdDirections/>}
                                sx={{
                                    display: 'flex',
                                    justifyContent: 'center'
                                }}
                        >Directions</Button>
                    </Box>
                </Drawer>

                <Drawer anchor="bottom" open={qrCodeScanningDrawerOpen}
                        onClose={toggleQrCodeScanningDrawer(false)}>
                    <Box className={'bottom-drawer-box'} p={2} sx={{
                        height: '100vh',
                        display: 'flex',
                        flexDirection: 'column',
                        justifyContent: 'space-between',
                    }}>
                        <Box className={'qr-code-scanner-top-bar'} style={{
                            display: 'flex',
                            justifyContent: 'space-between',
                            alignItems: 'center',
                        }}>
                            <IconButton onClick={toggleQrCodeScanningDrawer(false)}>
                                <MaterialIcons.MdClose/>
                            </IconButton>
                            <Typography>Scan QR Code</Typography>
                            <IconButton>
                                <MaterialIcons.MdMoreVert/>
                            </IconButton>
                        </Box>

                        <Box style={{
                            width: '100%',
                            height: '100%'
                        }}>
                            <QrReader
                                delay={300}
                                onError={handleScanError}
                                onScan={handleScan}
                                onResult={handleScan}
                                facingMode="environment"
                                containerStyle={{
                                    height: '100%',
                                    display: 'flex',
                                    justifyContent: 'center',
                                    alignItems: 'center'
                                }}>
                            </QrReader>
                        </Box>
                    </Box>
                </Drawer>


                <Drawer open={startingRideDrawerOpen} transitionDuration={0}
                        onClose={toggleStartingRideDrawer(false)}>
                    <Grid container className={'bottom-drawer-box'} p={2} sx={{
                        width: '100vw',
                        minHeight: '100vh',
                        maxHeight: '100vh',
                        display: 'flex',
                        flexDirection: 'column',
                        justifyContent: 'space-between',
                    }}>
                        <Grid item className={'qr-code-scanner-top-bar'} style={{
                            display: 'flex',
                            justifyContent: 'space-between',
                            alignItems: 'center'
                        }}>
                            <IconButton onClick={toggleStartingRideDrawer(false)}>
                                <MaterialIcons.MdArrowBack/>
                            </IconButton>
                            <Typography>Start Ride</Typography>
                            <IconButton>
                                <MaterialIcons.MdMoreVert/>
                            </IconButton>
                        </Grid>

                        <Grid item sx={{
                            display: 'flex',
                            flexDirection: 'column',
                            justifyContent: 'center',
                            alignItems: 'center'
                        }}>
                            <img
                                src={'https://static-00.iconduck.com/assets.00/alert-success-icon-512x512-lslkjbep.png'}
                                alt={'img'} style={{width: '100%', maxWidth: '300px'}}></img>
                            <p>QR Code Scan Successful</p>
                        </Grid>

                        <Grid item style={{
                            display: 'flex',
                            flexDirection: 'column',
                            justifyContent: 'center',
                            alignItems: 'center'
                        }}>
                            <Grid item>
                                <Button variant="contained" size={'medium'} color={'success'} onClick={startRide}
                                        sx={{
                                            width: '240px',
                                        }}>Start Ride</Button>
                            </Grid>
                            <Grid item sx={{paddingTop: '10px'}}>
                                <Button variant="contained" size={'medium'} color={'error'}
                                        onClick={closeStartingRideDrawer}
                                        sx={{
                                            width: '240px',
                                        }}>Cancel</Button>
                            </Grid>
                        </Grid>
                    </Grid>
                </Drawer>
            </Hidden>
        </>
    )
}