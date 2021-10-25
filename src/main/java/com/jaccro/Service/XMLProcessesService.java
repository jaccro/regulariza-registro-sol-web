package com.jaccro.Service;

public class XMLProcessesService {

    public String generate(String[] row) {
        String body = String.format(
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sol=\"http://solcot/\">"
                        + "<soapenv:Header/>" 
                        + "<soapenv:Body>" 
                        + "<sol:grabarSolCotToyota>" 
                        + "<idCli>%s</idCli>"
                        + "<idTipDoc>%s</idTipDoc>" 
                        + "<descli>%s</descli>" 
                        + "<apeCont>%s</apeCont>"
                        + "<nomCont>%s</nomCont>" 
                        + "<numTelFij>%s</numTelFij>" 
                        + "<numTelMov>%s</numTelMov>"
                        + "<emailCont>%s</emailCont>" 
                        + "<idLocCon>%s</idLocCon>" 
                        + "<codTel>%s</codTel>"
                        + "<idMar>%s</idMar>" 
                        + "<idModMar>%s</idModMar>" 
                        + "<idVerMod>%s</idVerMod>"
                        + "<idCol>%s</idCol>" 
                        + "<msgSol>%s</msgSol>" 
                        + "<otrOriSol>%s</otrOriSol>"
                        + "<idPlaDig>%s</idPlaDig>" 
                        + "<indEnt>%s</indEnt>" 
                        + "<indOpe>%s</indOpe>"
                        + "<otrOriSO>%s</otrOriSO>" 
                        + "<idDepRes>%s</idDepRes>" 
                        + "<indMAF>%s</indMAF>"
                        + "<impValVeh>%s</impValVeh>" 
                        + "<impCuoIni>%s</impCuoIni>" 
                        + "<numPlaMes>%s</numPlaMes>"
                        + "<impCuoMen>%s</impCuoMen>" 
                        + "<Clave_Acceso>w!20!8_tDp1</Clave_Acceso>"
                        + "<indTraDat>%s</indTraDat>" 
                        + "<aceptaSeguro>%s</aceptaSeguro>"
                        + "<precioSeguroToyota>%s</precioSeguroToyota>" 
                        + "<toyotaValue>%s</toyotaValue>"
                        + "<precioSolesSeguroToyota>%s</precioSolesSeguroToyota>" 
                        + "<impSolValVeh>%s</impSolValVeh>"
                        + "<impSolCuoIni>%s</impSolCuoIni>" 
                        + "<impSolCuoMen>%s</impSolCuoMen>"
                        + "<impCuoFin>%s</impCuoFin>" 
                        + "<impSolCuoFin>%s</impSolCuoFin>"
                        + "<impCuoIrresistible>%s</impCuoIrresistible>"
                        + "<impSolCuoIrresistible>%s</impSolCuoIrresistible>"
                        + "<aceptaToyotaValue>%s</aceptaToyotaValue>" 
                        + "<importeToyotaValue>%s</importeToyotaValue>"
                        + "<importeSolesToyotaValue>%s</importeSolesToyotaValue>"
                        + "<descripcionToyotaValue>%s</descripcionToyotaValue>" 
                        + "</sol:grabarSolCotToyota>"
                        + "</soapenv:Body>" 
                        + "</soapenv:Envelope>",
                row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10], row[11], row[12],
                row[13], row[14], row[15], row[16], row[17], row[18], row[19], row[20], row[21], row[22], row[23],
                row[24], row[25], row[26], row[29], row[30], row[31], row[32], row[33], row[34], row[35], row[36],
                row[37], row[38], row[39], row[40], row[41], row[42], row[43], row[44]);

        return body;
    }
}
