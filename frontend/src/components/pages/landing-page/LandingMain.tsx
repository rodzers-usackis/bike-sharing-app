import * as React from 'react';
import {styled} from '@mui/material/styles';
import {useNavigate} from "react-router-dom";
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Button, { ButtonProps } from '@mui/material/Button';
import { deepOrange } from '@mui/material/colors';
import Logo from "../../../assets/svg/landing-page/Ride a bicycle-pana.svg"
import * as MaterialIcons from 'react-icons/md'
import HowItWorks from "../how-it-works/HowItWorks";
import Prices from "../prices/Prices";
import {useMediaQuery} from "@mui/material";

export default function LandingMain() {
    const matches = useMediaQuery('(max-width:599px)');
    const navigate = useNavigate();

    const ColorButton = styled(Button)<ButtonProps>(({ theme }) => ({
        color: theme.palette.getContrastText(deepOrange[500]),
        backgroundColor: deepOrange[500],
        '&:hover': {
            backgroundColor: deepOrange[700],
        },
    }));

    /*
    Note, I will implement the HowItWorks and Prices Components differently,
    this was a last moment idea, and I just wanted to check out how it looks.
    */
    return (
        <>
            {/* Hero unit */}
            <Grid container py={3} sx={{
                minHeight: matches ? 'calc(100vh - 112px)' : 'calc(100vh - 64px)',
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
            }}>
                <Grid item lg={4} md={12} sm={12} xs={12} sx={{
                    height: '100%',
                    justifyContent: 'center',
                    alignItems: 'center',
                    display: 'flex'
                }}>
                    <Box>
                        <Container maxWidth="md" sx={{}}>
                            <Typography
                                component="h1"
                                variant="h2"
                                align="left"
                                color="text.primary"
                                gutterBottom
                                justifyContent={'flex-start'}
                            >
                                VeloCity
                            </Typography>
                            <Typography variant="h5" align="left" color="text.secondary" paragraph>
                                Some short and appealing text about our bike sharing service.
                                Make it short and sweet, but not too short so folks
                                don't simply skip over it entirely.
                            </Typography>
                            <Stack
                                sx={{pt: 4}}
                                direction="row"
                                spacing={2}
                                justifyContent="start"
                            >
                                <ColorButton variant={'contained'} sx={{
                                    fontSize: 18,
                                    borderRadius: 0,
                                }} onClick={() => navigate('/how-it-works')}>Get started with VeloCity</ColorButton>
                            </Stack>
                            <Stack>
                                <p className={'landing-page-sign-up-text'}><a onClick={() => navigate('/prices')}>Sign up for your day pass, week pass or annual card&nbsp;&nbsp;&nbsp;<MaterialIcons.MdArrowForwardIos /></a></p>
                            </Stack>
                        </Container>
                    </Box>
                </Grid>
                <Grid item lg={8} md={10} sm={10} xs={10}>
                    <Box sx={{
                        height: '100%',
                        justifyContent: 'center',
                        alignItems: 'center',
                        alignContent: 'center',
                        display: 'flex',
                    }}>
                        <img src={Logo} alt={'svg'} style={{
                            maxHeight: matches ? 'calc(100vh - 112px)' : 'calc(100vh - 64px)',
                            width: '100%'
                        }}/>
                    </Box>
                </Grid>
            </Grid>
            <HowItWorks></HowItWorks>
            <Prices></Prices>
        </>
    )
}