export interface DefectFormInputs {
    description: string;
    customerId: string;
    vehicleId: string;
    isUnusable: boolean;
    hasBrokenWheel: boolean;
    hasBrokenBrake: boolean;
    hasBrokenLight: boolean;
    hasFlatTyre: boolean;
    hasOtherDefect: boolean;
    photo: string | undefined;
}