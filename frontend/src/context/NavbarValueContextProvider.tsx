import React, { ReactElement, useState } from 'react'
import NavbarValueContext from './NavbarValueContext'

interface WithChildren {
    children: ReactElement | ReactElement[]
}

export default function NavbarValueContextProvider({ children }: WithChildren) {
    const [currentValue, setCurrentValue] = useState(99)

    return (
        <NavbarValueContext.Provider value={{currentValue, setCurrentValue}}>
            {children}
        </NavbarValueContext.Provider>
    )
}
