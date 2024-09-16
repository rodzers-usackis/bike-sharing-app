import { createContext } from 'react'

export interface NavbarValueContext {
    currentValue: number
    setCurrentValue: (value: number) => void
}

export default createContext<NavbarValueContext>({
    currentValue: 99,
    setCurrentValue: () => {},
})
