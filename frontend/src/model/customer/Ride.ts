
export interface Ride {
    uuid: string
    customerUUID: string
    startTime: Date
    startStation: string
    endTime: Date
    endStation: string
    totalCost: number
    activationFee: number
    pricePerMinute: number
}