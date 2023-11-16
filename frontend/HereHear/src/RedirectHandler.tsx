import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";

export default function RedirectHandler() {
    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        const queryParams = new URLSearchParams(location.search);
        const token = queryParams.get("token");
        const memberId = queryParams.get("id");

        if (token) {
            localStorage.setItem("token", token);

            if (memberId) {
                localStorage.setItem("memberId", memberId);

                if (location.pathname.startsWith("/oauth2/redirect")) {
                    navigate("/core");
                }
            }
        }
    }, [location, navigate]);

    return null;
}
