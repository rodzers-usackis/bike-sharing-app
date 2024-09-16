import './App.css';
import TopNavbar from "./components/navbar/desktop/TopNavbar";
import LandingMain from "./components/pages/landing-page/LandingMain";
import Prices from "./components/pages/prices/Prices";
import HowItWorks from "./components/pages/how-it-works/HowItWorks";
import Map from "./components/pages/Map";
import Dashboard from "./components/pages/dashboard/Dashboard";
import Profile from "./components/pages/Profile";
import Station from "./components/pages/Station";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {orange} from '@mui/material/colors'
import {createTheme, ThemeProvider} from '@mui/material'
import {QueryClient, QueryClientProvider} from 'react-query'
import {DefectReportForm} from "./components/pages/defect-report/DefectReportForm";
import {DefectReportPage} from "./components/pages/defect-report/DefectReportPage";
import BottomNavbar from "./components/navbar/mobile/BottomNavbar";
import NavbarValueContextProvider from "./context/NavbarValueContextProvider";
import {MaintenanceReportPage} from "./components/pages/maintenance-reports/MaintenanceReportPage";
import RideHistory from "./components/pages/RideHistory";
import UserInRideContextProvider from "./context/UserInRideContextProvider";

const queryClient = new QueryClient()

const theme = createTheme({
    palette: {
        primary: {
            main: orange[500],
        },
        secondary: {
            main: '#212121',
        },
        background: {
            default: '#f5f5f5',
        },
        text: {
            primary: '#212121',
            secondary: '#757575',
        },
    },
});

function App() {
    // Return
    return (
        <UserInRideContextProvider>
            <ThemeProvider theme={theme}>
                <QueryClientProvider client={queryClient}>
                    <BrowserRouter>
                        <NavbarValueContextProvider>
                            <TopNavbar/>
                            <Routes>
                                <Route path="/prices" element={<Prices/>}/>
                                <Route path="/map" element={<Map/>}/>
                                <Route path="/stations/:id" element={<Station/>}/>
                                <Route path="/how-it-works" element={<HowItWorks/>}/>
                                <Route path="/report-defect" element={<DefectReportPage/>}/>
                                <Route path="/prediction" element={<Dashboard/>}/>
                                <Route path="/profile" element={<Profile/>}/>
                                <Route path="/" element={<LandingMain/>}/>
                                <Route path="/maintenance-reports" element={<MaintenanceReportPage/>}/>
                            </Routes>
                            <BottomNavbar/>
                        </NavbarValueContextProvider>
                    </BrowserRouter>
                </QueryClientProvider>
            </ThemeProvider>
        </UserInRideContextProvider>
    )
}

export default App;
