import React, {useContext} from "react";
import {useNavigate} from 'react-router';
import {
    AppBar,
    Hidden,
    IconButton,
    Toolbar,
    useMediaQuery
} from "@mui/material";
import {TopNavbarItems} from "./TopNavbarItems";
import Button from '@mui/material/Button';
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import {TopNavbarIcons} from "./TopNavbarIcons";
import NavbarValueContext from "../../../context/NavbarValueContext";

// TODO: if you select bottom nav bar icon and refresh page, it switches to home, maybe check for window href parameter and do something
export default function TopNavbar() {
    const {currentValue, setCurrentValue} = useContext(NavbarValueContext);
    const [auth, setAuth] = React.useState(true);
    const navigate = useNavigate();
    const matches = useMediaQuery('(max-width:599px)');

    const handleClick = (path: string) => {
        navigate(path)
    };

    return (
        <><AppBar className={'appbar-paper'} sx={{position: 'sticky', top: 0, left: 0, right: 0}}>
            <Toolbar>
                <Grid container justifyContent='space-between' alignItems='center'>
                    <Grid item>
                        <IconButton
                            size="large"
                            aria-label={TopNavbarIcons[0].className}
                            onClick={() => {
                                handleClick(TopNavbarIcons[0].url);
                                setCurrentValue(0);
                            }}
                            color = 'inherit'
                        >
                            {TopNavbarIcons[0].icon}
                        </IconButton>
                    </Grid>

                    <Grid item lg={4} md={6} sm={8}>
                        <Hidden mdDown={matches}>
                            <Box sx={{flexGrow: 1, display: 'flex', justifyContent: 'space-between'}}>
                                {TopNavbarItems.map((page) => (
                                    <Button
                                        key={page.title}
                                        onClick={() => {
                                            handleClick(page.url);
                                            setCurrentValue(page.value);
                                        }}
                                        sx={{color: 'white', padding: '0', fontWeight: '600'}}
                                    >
                                        {page.title}
                                    </Button>
                                ))}
                            </Box>
                        </Hidden>
                    </Grid>

                    <Grid item>
                        {auth && (
                            <div>
                                <IconButton
                                    size="large"
                                    aria-label={TopNavbarIcons[1].className}
                                    onClick={() => {
                                        handleClick(TopNavbarIcons[1].url);
                                        setCurrentValue(99);
                                    }}
                                    color="inherit"
                                >
                                    {TopNavbarIcons[1].icon}
                                </IconButton>
                            </div>
                        )}
                    </Grid>
                </Grid>
            </Toolbar>
        </AppBar>
        </>
    );
}
