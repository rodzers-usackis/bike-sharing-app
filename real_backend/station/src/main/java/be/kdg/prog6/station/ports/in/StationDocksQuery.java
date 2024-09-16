package be.kdg.prog6.station.ports.in;

import java.util.List;

public interface StationDocksQuery {
    int getNumberOfDocksAvailable(StationDocksCommand stationDocksCommand);

    int getNumberOfDocksInUse(StationDocksCommand stationDocksCommand);
}
