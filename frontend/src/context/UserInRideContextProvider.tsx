import React, { ReactElement, useState } from 'react'
import UserInRideContext from './UserInRideContext'

interface WithChildren {
    children: ReactElement | ReactElement[]
}

export default function UserInRideContextProvider({ children }: WithChildren) {
    const [userInRide, setUserInRide] = useState(false)

    return (
        <UserInRideContext.Provider value={{userInRide, setUserInRide}}>
            {children}
        </UserInRideContext.Provider>
    )
}
