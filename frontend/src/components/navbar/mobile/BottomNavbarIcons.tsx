import React from 'react'
import * as MaterialIcons from 'react-icons/md'

export const BottomNavbarIcons = [
    {
        icon: <MaterialIcons.MdHome />,
        url: '/',
        className: 'navbar-icons',
        label: 'Home'
    },
    {
        icon: <MaterialIcons.MdMap />,
        url: '/map',
        className: 'navbar-icons',
        label: 'Map'
    },
    {
        icon: <MaterialIcons.MdAttachMoney />,
        url: '/prices',
        className: 'navbar-icons',
        label: 'Prices'
    },
    {
        icon: <MaterialIcons.MdInfo />,
        url: '/how-it-works',
        className: 'navbar-icons',
        label: 'Info'
    }
]