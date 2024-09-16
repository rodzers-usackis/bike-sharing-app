export interface MaintenanceVehicleDefectReport {
    uuid: string;
    vehicleId: string;
    description: string;
    customerId: string;
    timestamp: Date
    isUnusable: boolean;
    hasBrokenWheel: boolean;
    hasFlatTyre: boolean;
    hasBrokenLight: boolean;
    hasBrokenBrake: boolean;
    hasOtherDefect: boolean;
    status: string;
    photo: string | undefined;
}