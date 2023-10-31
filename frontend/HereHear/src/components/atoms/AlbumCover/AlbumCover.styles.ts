import styled from "styled-components";

const AlbumCoverWrapper = styled.div`
    width: 224px;
    height: 224px;
    margin-top: 20px;
    background: ${({ theme }) => theme.gradient.gradient1};
    border-radius: 112px;
    box-shadow: ${({ theme }) => theme.shadow.shadow_album};
    display: flex;
    justify-content: center;
    align-items: center;
`;

export { AlbumCoverWrapper };
