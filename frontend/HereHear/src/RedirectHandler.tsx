import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";

export default function RedirectHandler() {
    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        const queryParams = new URLSearchParams(location.search);
        const token = queryParams.get("token");

        if (token) {
            localStorage.setItem("token", token);

            if (location.pathname.startsWith("/oauth2/redirect")) {
                // 필요한 경우 토큰을 사용하여 상태를 설정하거나 다른 처리를 수행
                // ...

                navigate("/core");
            }
        }
    }, [location, navigate]);

    return null;
}
