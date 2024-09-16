import * as React from 'react';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import {Card, CardContent, CardActions, Divider, useMediaQuery} from "@mui/material";
import {PricingItems} from "./PricingItems";
import {deepOrange} from '@mui/material/colors';

export default function Prices() {
    const matches = useMediaQuery('(max-width:599px)');

    return (
        <>
            <Grid container sx={{
                minHeight: matches ? 'calc(100vh - 112px)' : 'calc(100vh - 64px)'
            }}>
                <Container disableGutters maxWidth="sm" sx={{pt: 8}}>
                    <Typography
                        component="h2"
                        variant="h2"
                        align="center"
                        color="text.primary"
                        gutterBottom
                        fontWeight={500}
                    >
                        Prices
                    </Typography>
                    <Typography variant="h4" align="center" component="p"
                                style={{color: deepOrange[500], fontWeight: 500}}>
                        Choose your pass type
                    </Typography>
                </Container>
                <Container maxWidth="xl" sx={{
                    paddingBottom: '48px'
                }}>
                    <Grid container sx={{margin: '0', width: '100%', display: 'flex', justifyContent: 'center'}}>
                        {PricingItems.map((tier) => (
                            // Enterprise card is full width at sm breakpoint
                            <Grid
                                item
                                key={tier.title}
                                xl={3}
                                lg={3}
                                md={5}
                                sm={6}
                                xs={10}
                                px={{xs: 3, sm: 2, md: 3}}
                                py={{xs: 2, sm: 2, md: 3}}
                            >
                                <Card elevation={8} style={{height: '100%'}}>
                                    <CardContent>
                                        <Box
                                            sx={{
                                                justifyContent: 'center',
                                                alignItems: 'baseline',
                                                mb: 2,
                                                textAlign: 'center'
                                            }}
                                        >
                                            <p>
                                                <Typography component="h4" variant="h4"
                                                            style={{color: deepOrange[500], fontWeight: 500}}>
                                                    {tier.title}
                                                </Typography>
                                            </p>

                                            <p>
                                                <Typography color="text.primary">
                                                    {tier.description}
                                                </Typography>
                                            </p>

                                            <Button
                                                variant={'contained'}
                                                sx={{
                                                    borderRadius: 0,
                                                    width: '100px',
                                                    fontSize: '12px',
                                                    padding: 1
                                                }}
                                            >More Info</Button>

                                            <p>
                                                <Typography component="h2" variant="h3" color="text.primary">
                                                    € {tier.price}
                                                </Typography>
                                            </p>
                                        </Box>
                                        <p>
                                            {tier.disclaimer.map((line) => (
                                                <Typography
                                                    variant="subtitle1"
                                                    align="center"
                                                    key={line}
                                                >
                                                    {line}
                                                </Typography>
                                            ))}
                                        </p>
                                    </CardContent>
                                    <Divider/>
                                    <CardActions sx={{display: 'flex', justifyContent: 'center'}}>
                                        <Button
                                            variant={'contained'}
                                            sx={{
                                                borderRadius: 0,
                                                maxWidth: '250px'
                                            }}
                                        >
                                            {tier.button_text}
                                        </Button>
                                    </CardActions>
                                </Card>
                            </Grid>
                        ))}
                    </Grid>
                </Container>
            </Grid>
        </>
    )
}