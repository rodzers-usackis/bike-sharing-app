import React, {useContext, useEffect} from "react";
import {useNavigate} from 'react-router';
import {
    BottomNavigation, BottomNavigationAction,
    Hidden,
    useMediaQuery
} from "@mui/material";
import {BottomNavbarIcons} from "./BottomNavbarIcons";
import NavbarValueContext from "../../../context/NavbarValueContext";

// TODO: if you select an item from top navbar, bottom navbar doesn't get updated
// Maybe someone else has an idea on how to do this ?
export default function BottomNavbar() {
    const {currentValue, setCurrentValue} = useContext(NavbarValueContext);
    const navigate = useNavigate();
    const matches = useMediaQuery('(max-width:599px)');

    const handleClick = (path: string) => {
        navigate(path)
    };

    // I couldn't think of a better way to do this.
    // Issue was that when I navigated backwards or forwards, the Bottom Navigation values didn't update
    // As well as with a page reload
    // This is my temporary solution
    useEffect(() => {
        function handleLocationChange() {
            const newPathName = window.location.pathname

            switch(newPathName) {
                case '/':
                    setCurrentValue(0)
                    break;
                case '/map':
                    setCurrentValue(1)
                    break;
                case '/prices':
                    setCurrentValue(2)
                    break;
                case '/how-it-works':
                    setCurrentValue(3)
                    break;
                default:
                    setCurrentValue(99)
                    break;
            }
        }


        window.addEventListener('popstate', handleLocationChange);
        window.addEventListener('load', handleLocationChange);

        return () => {
            window.removeEventListener('popstate', handleLocationChange);
            window.removeEventListener('load', handleLocationChange);
        };
    },[]);

    return (
        <Hidden smUp={!matches}>
            <BottomNavigation
                className={'bottom-nav'}
                showLabels
                value={currentValue}
                sx={{position: 'fixed', bottom: 0, left: 0, right: 0}}
            >
                {BottomNavbarIcons.map((item, index) => {
                    return (
                        <BottomNavigationAction key={item.label} label={item.label} icon={item.icon}
                                                sx={{fontSize: '25px'}}
                                                onClick={() => {
                                                    handleClick(item.url);
                                                    setCurrentValue(index)
                                                }}/>
                    );
                })}
            </BottomNavigation>
        </Hidden>
    )
}

