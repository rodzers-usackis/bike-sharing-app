import * as React from 'react';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import {Card, CardContent, useMediaQuery} from "@mui/material";
import {deepOrange} from '@mui/material/colors';
import {HowItWorksItems} from "./HowItWorksItems";

export default function HowItWorks() {
    const matches = useMediaQuery('(max-width:599px)');

    return (
        <>
            <Grid container sx={{
                minHeight: matches ? 'calc(100vh - 112px)' : 'calc(100vh - 64px)'
            }}>
                <Container disableGutters maxWidth="lg" sx={{pt: 8, pb: 6}}>
                    <Typography
                        component="h2"
                        variant="h2"
                        align="center"
                        color="text.primary"
                        gutterBottom
                        fontWeight={500}
                    >

                    </Typography>
                    <Typography variant="h4" align="center" component="p"
                                style={{color: deepOrange[500], fontWeight: 500}}>
                        VeloCity is the best bike sharing service for all of your commuting needs
                    </Typography>
                    <p>
                        <Typography variant="h6" align="center" component="p" style={{fontWeight: 500}}>
                            Become a member by signing up for a pass today!
                        </Typography>
                    </p>
                </Container>
                <Container maxWidth="xl">
                    <Grid container spacing={7} sx={{
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'start'
                    }}>
                        {HowItWorksItems.map((item) => (
                            // Enterprise card is full width at sm breakpoint
                            <Grid
                                item
                                key={item.title}
                                xl={4}
                                lg={4}
                                md={4}
                                sm={10}
                                xs={10}
                            >
                                <Card elevation={0} style={{height: '100%'}}>
                                    <CardContent>
                                        <Box
                                            sx={{
                                                justifyContent: 'center',
                                                alignItems: 'baseline',
                                                mb: 2,
                                                textAlign: 'center'
                                            }}
                                        >
                                            <img src={item.image} alt={'svg'} style={{}}/>
                                            <p>
                                                <Typography component="h4" variant="h4"
                                                            style={{color: deepOrange[500], fontWeight: 500}}>
                                                    {item.title}
                                                </Typography>
                                            </p>

                                            <p>
                                                <Typography color="text.primary" fontSize={'16px'}>
                                                    {item.description}
                                                </Typography>
                                            </p>
                                        </Box>
                                    </CardContent>
                                </Card>
                            </Grid>
                        ))}
                    </Grid>
                </Container>
            </Grid>
        </>
    )
}