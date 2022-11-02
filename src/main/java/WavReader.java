import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ListIterator;

public class WavReader {
    int[] DATA;
    Integer DATA_POINTER = 0;
    ArrayList<byte[]> params = new ArrayList<>();
    {
    params.add(new byte[4]);   //chunkId
    params.add(new byte[4]);    //chunkSize
    params.add(new byte[4]);    //format
    params.add(new byte[4]);    //subchunk1Id
    params.add(new byte[4]);    //Subchunk1Size
    params.add(new byte[2]);    //audioFormat
    params.add(new byte[2]);    //numChannels
    params.add(new byte[4]);    //sampleRate
    params.add(new byte[4]);    //byteRate
    params.add(new byte[2]);    //blockAlign
    params.add(new byte[2]);    //bitsPerSample
    params.add(new byte[4]);    //subchunk2Id
    params.add(new byte[4]);    //subchunk2Size
    }




    WavReader(String filepath){
        try{
            File soundFile = new File(filepath);
            byte[] fileData = Files.readAllBytes(soundFile.toPath());
            ListIterator<byte[]> iterator = params.listIterator();
            int pointer = 0;
            while(iterator.hasNext()){
                byte[]temp = iterator.next();
                for(int i = 0; i<temp.length; i++){
                    temp[i] = fileData[pointer+i];
                }
                pointer = pointer + temp.length;
            }

            for(int i = pointer; i<fileData.length;i++){
                if((fileData[i] == 0x44 || fileData[i] == 0x64 )&& (i+4)<fileData.length){
                    if(fileData[i+1]==0x41 || fileData[i+1]==0x61){
                        if(fileData[i+2]==0x54 || fileData[i+2]==0x74){
                            if(fileData[i+3]==0x41 || fileData[i+3]==0x61){
                               DATA_POINTER = i+4;
                            }
                        }
                    }
                }
            }
            int[] mainData = new int[fileData.length - DATA_POINTER];
            for (int i = 0; i<fileData.length-DATA_POINTER; i++){
                mainData[i] = fileData[i+DATA_POINTER];
            }
            DATA = mainData;

        }catch(Exception e){
            System.out.println("Header init failed");
        }
    }

    public void getHeader() {
        ListIterator<byte[]> iterator = params.listIterator();
        while(iterator.hasNext()){
        try {
            System.out.println(bytesToHex(iterator.next()));
        }catch(Exception e){
            System.out.println("Failed in getHeader");
        }
        }
    }

    public void readWav(String filepath){

        try {
           File soundFile = new File(filepath);
           byte[] byteRaw = Files.readAllBytes(soundFile.toPath());


        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }



    private final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
