interface AddressUUID {
    uuid: string
}

interface Address {
    addressUUID: AddressUUID,
    city: string,
    streetName: string,
    streetNumber: number
}

// The UUID parameters will most likely change in the future, currently they are NULL
interface Docks {
    dockAvailable: boolean,
    dockInUse: boolean,
    dockNumber: number,
    stationUUID: string,
    vehicleUUID: string
}

interface LocationUUID {
    uuid: string
}

interface Location {
    latitude: number,
    longitude: number
    locationUUID: LocationUUID,
    name: string
}

interface UUID {
    uuid: string
}

export interface Station {
    address: Address,
    docks: Docks,
    location: Location,
    uuid: UUID
}