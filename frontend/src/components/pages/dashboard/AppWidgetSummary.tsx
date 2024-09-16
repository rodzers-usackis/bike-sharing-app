import {alpha, styled} from '@mui/material/styles';
import {Card, PropTypes, Typography} from '@mui/material';
// ----------------------------------------------------------------------

const StyledIcon = styled('div')(({ theme }) => ({
    margin: 'auto',
    display: 'flex',
    borderRadius: '50%',
    alignItems: 'center',
    width: theme.spacing(8),
    height: theme.spacing(8),
    justifyContent: 'center',
    marginBottom: theme.spacing(3),
}));

// ----------------------------------------------------------------------

interface PropTypes {
    color: string,
    icon: any,
    added_text: string,
    title: string,
    total: number,
    sx: object,
}

export default function AppWidgetSummary({ title, total, icon, added_text, color = 'primary', sx, ...other } : PropTypes) {
    return (
        <Card
            sx={{
                py: 5,
                boxShadow: 0,
                textAlign: 'center',
                color: (theme) => theme.palette.secondary.main,
                backgroundColor: (theme) => theme.palette.primary.light,
                ...sx,
            }}
            {...other}
        >
            <StyledIcon
                sx={{
                    color: (theme) => theme.palette.secondary.main,
                    backgroundImage: (theme) =>
                        `linear-gradient(135deg, ${alpha(theme.palette.secondary.main, 0)} 0%, ${alpha(
                            theme.palette.secondary.main,
                            0.15
                        )} 100%)`,
                }}
            >
                {icon}
            </StyledIcon>

            <Typography variant="h3">{(total)}{added_text}</Typography>

            <Typography variant="subtitle2" sx={{opacity: 0.72}}>
                {title}
            </Typography>
        </Card>
    );
}