package com.example.echo.youtube_conf;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.echo.service.ApiAccount.ApiAccountService;

public class Youtube_key {

    @Autowired
    ApiAccountService apiAccountService;

    public String get_access() {
        String youtube_key = "AIzaSyDz1TbxBmwERK2DC9N7BII7E_DQjfaN9wc";
        return youtube_key;
    }

    public String[] get_accessKey(ApiAccountService apiAccountService) {

        apiAccountService.updateFlag();

        String id = apiAccountService.useAccount();
        String quata = apiAccountService.getAcQuata(id);
        String[] returnbox = new String[3];
        returnbox[0] = id;
        returnbox[2] = quata;

        if(id.equals("A01")){
            String youtube_key = "AIzaSyDz1TbxBmwERK2DC9N7BII7E_DQjfaN9wc";
            returnbox[1] = youtube_key;
            return returnbox;
        }

        if(id.equals("A02")){
            System.out.println("fuckin fuckin");
            String youtube_key = "AIzaSyBG66vFnVKDaraljqSxacVydPxfvWMRMfQ";
            returnbox[1] = youtube_key;
            return returnbox;
        }

        if(id.equals("A03")){
            String youtube_key = "AIzaSyBcsN0MmSbQ5x45701_0NExRGNdORTqq5M";
            returnbox[1] = youtube_key;
            return returnbox;
        }

        if(id.equals("A04")){
            String youtube_key = "AIzaSyDLYI3-vd4valGYX7ukNNXLIEM7zUTgRBQ";
            returnbox[1] = youtube_key;
            return returnbox;
        }

        if(id.equals("A05")){
            String youtube_key = "AIzaSyAVM1EtapxfjHjVIloN2g4H9EAMkbi5JHs";
            returnbox[1] = youtube_key;
            return returnbox;
        }

        returnbox[1] = "notFound";
            return returnbox;
    }
    public void changeFalseFlag(String id, int quata, ApiAccountService apiAccountService){

        apiAccountService.updateQuata(id, quata);
        System.out.println(quata);

        if(quata >= 9400){
            apiAccountService.changeFalseFlag(id);
        }
        
    }
}