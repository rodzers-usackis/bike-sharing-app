interface BikeID {
    uuid: string
}

interface CostPerMinute {
    amount: number
}

interface ActivationCost {
    amount: number
}

interface BikePrice {
    costPerMinute: CostPerMinute,
    activationCost: ActivationCost
}

export interface Bike {
    id: BikeID,
    startDate: string,
    lastMaintenanceDate: string,
    model: string,
    distanceTraveledInMeters: number,
    vehicleState: string,
    price: BikePrice
}