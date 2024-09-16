import { createContext } from 'react'

export interface UserInRideContext {
    userInRide: boolean
    setUserInRide: (value: boolean) => void
}

export default createContext<UserInRideContext>({
    userInRide: false,
    setUserInRide: () => {},
})
