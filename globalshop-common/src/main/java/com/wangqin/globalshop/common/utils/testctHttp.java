package com.wangqin.globalshop.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

public class testctHttp
{

	public static void main(String[] args) throws  Exception
	{
        String soapActionString = "https://weblabeling.gls-italy.com/IlsWebService.asmx/AddParcel";//Soap 1.1中使用  
        String XMLInfoParcel="XMLInfoParcel="+"<Info><SedeGls>AL</SedeGls><CodiceClienteGls>2557</CodiceClienteGls><PasswordClienteGls>BUYGLS2557</PasswordClienteGls>"
        		+ "<Parcel><CodiceContrattoGls>3023</CodiceContrattoGls><RagioneSociale>Paolo Rossi</RagioneSociale><Indirizzo>Via Dante,120</Indirizzo><Localita>Piacenza</Localita><Zipcode>29100</Zipcode><Provincia>PC</Provincia><Bda>0000000001</Bda><DataDocumentoTrasporto>120523</DataDocumentoTrasporto><Colli>1</Colli><Incoterm>0</Incoterm><PesoReale>10,1</PesoReale><ImportoContrassegno>100,1</ImportoContrassegno><NoteSpedizione>poof shipment notes</NoteSpedizione><TipoPorto>F</TipoPorto><Assicurazione>1000,1</Assicurazione><PesoVolume>0,1</PesoVolume>"
        		+ "<TipoCollo>0</TipoCollo><RiferimentoCliente>0001</RiferimentoCliente><NoteAggiuntive>proof additional notes</NoteAggiuntive><CodiceClienteDestinatario></CodiceClienteDestinatario><Email>andrea.zaffignani@gls-italy.com</Email>"
        		+ "<Cellulare1>3480000001</Cellulare1><ServiziAccessori>16</ServiziAccessori><ModalitaIncasso>CONT</ModalitaIncasso><DataPrenotazioneGDO>110923</DataPrenotazioneGDO>"
        		+ "<OrarioNoteGDO>11:30</OrarioNoteGDO><GeneraPdf>1</GeneraPdf><ContatoreProgressivo>000222333</ContatoreProgressivo><IdentPIN>123456789012</IdentPIN><AssicurazioneIntegrativa>A</AssicurazioneIntegrativa></Parcel><Parcel><CodiceContrattoGls>6929</CodiceContrattoGls><RagioneSociale>Paolo Bianchi</RagioneSociale><Indirizzo>Via Dante,120</Indirizzo><Localita>Montale</Localita><Zipcode>29100</Zipcode><Provincia>PC</Provincia>"
        		+ "<bda>12345678901</bda><DataDocumentoTrasporto>120523</DataDocumentoTrasporto><Colli>1</Colli><Incoterm>0</Incoterm><PesoReale>10,1</PesoReale><ImportoContrassegno>100,1</ImportoContrassegno><NoteSpedizione>proof shipment notes</NoteSpedizione><TipoPorto>F</TipoPorto><Assicurazione>1000,1</Assicurazione><PesoVolume>0,1</PesoVolume><TipoCollo>0</TipoCollo><RiferimentoCliente>0001</RiferimentoCliente><NoteAggiuntive>proof additional notes</NoteAggiuntive><CodiceClienteDestinatario></CodiceClienteDestinatario><Email>andrea.zaffignani@gls-italy.com</Email><Cellulare1>3480000001</Cellulare1>"
        		+ "<ServiziAccessori>16</ServiziAccessori><ModalitaIncasso>CONT</ModalitaIncasso><DataPrenotazioneGDO>110923</DataPrenotazioneGDO><OrarioNoteGDO>11:30</OrarioNoteGDO><GeneraPdf>1</GeneraPdf><ContatoreProgressivo >000222334</ContatoreProgressivo><IdentPIN>123456789013</IdentPIN><AssicurazioneIntegrativa>A</AssicurazioneIntegrativa></Parcel></Info>";
        String result = sendPost(soapActionString,XMLInfoParcel);
        System.out.println("result:" + result);	 
	}
	/**
     * 从输入流中读取数据
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }

	private static String getXmlInfo() {  
        StringBuilder sb = new StringBuilder();
        sb.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");    
        sb.append("<soap:Body>"); 
        sb.append("<AddParcel xmlns=\"http://weblabeling.gls-italy.com/\">");
        sb.append("<XMLInfoParcel>"+getXmlInfo2()+"</XMLInfoParcel>");
        sb.append("</AddParcel>");
        sb.append("</soap:Body>");
        sb.append("</soap:Envelope>");
        return sb.toString();  
    }  
	
	private static String getXmlInfo2() {  
        StringBuilder sb = new StringBuilder();
        sb.append("<SedeGls>WW</SedeGls>");  
        sb.append("<CodiceClienteGls>019440</CodiceClienteGls>");  
        sb.append("<PasswordClienteGls>pippo</PasswordClienteGls>");  
        sb.append("<Parcel>");  
        sb.append("<CodiceContrattoGls>6929</CodiceContrattoGls>");  
        sb.append("<RagioneSociale>Paolo Rossi</RagioneSociale>");  
        sb.append("<Indirizzo>ViaDante120</Indirizzo>");  
        sb.append("<Localita>Piacenza</Localita>");  
        sb.append("<Zipcode>29100</Zipcode>");  
        sb.append("<Provincia>PC</Provincia>");  
      //sb.append("<Bda>0000000001</Bda>");  
      //sb.append("<Datadocumentotrasporto>120523</Datadocumentotrasporto>");  
        sb.append("<Colli>1</Colli>");  
      //sb.append("<Incoterm>0</Incoterm>"); 
        sb.append("<PesoReale>101</PesoReale>"); 
     // sb.append("<Importocontrassegno>1001</Importocontrassegno>"); 
     //   sb.append("<NoteSpedizione>poof shipment notes</NoteSpedizione>"); 
        sb.append("<TipoPorto>F</TipoPorto>"); 
      //  sb.append("<Assicurazione>10001</Assicurazione>"); 
      //  sb.append("<PesoVolume>01</PesoVolume>"); 
      //  sb.append("<RiferimentoCliente>0001</RiferimentoCliente>"); 
      //  sb.append("<NoteAggiuntive>proof additional notes</NoteAggiuntive>");
      //  sb.append("<CodiceClienteDestinatario></CodiceClienteDestinatario>");
        sb.append("<TipoCollo>0</TipoCollo>");
     //   sb.append("<Francoanticipata></Francoanticipata>"); 
     //   sb.append("<Email>andrea.zaffignani@gls-italy.com</Email>");
     //   sb.append("<Cellulare1>3480000001</Cellulare1>");
     //   sb.append("<ServiziAccessori>16</ServiziAccessori>");
     //   sb.append("<ModalitaIncasso>CONT</ModalitaIncasso>");
     //   sb.append("<DataPrenotazioneGDO>110923</DataPrenotazioneGDO>");
     //   sb.append("<OrarioNoteGDO>11:30</OrarioNoteGDO>");
     //   sb.append("<GeneraPdf>1</GeneraPdf>");
    //    sb.append("<ContatoreProgressivo>000222333</ContatoreProgressivo>");
     //   sb.append("<IdentPIN >123456789012</IdentPIN>");
    //    sb.append("<AssicurazioneIntegrativa>A</AssicurazioneIntegrativa>");
        sb.append("</Parcel>");
        return sb.toString();  
    }  
	public static String sendPost(String url1,String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
		    byte[] buf = param.getBytes();
			URL realUrl = new URL(url1);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("Host", "weblabeling.gls-italy.com");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length",String.valueOf(buf.length));//这句话可以不用写，即使是随便写  
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			//println("发送 POST 请求出现异常！" + e);
			//e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
		if (str != null) {
			// 用默认字符编码解码字符串。
			byte[] bs = str.getBytes();
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	

	
	
	
}
