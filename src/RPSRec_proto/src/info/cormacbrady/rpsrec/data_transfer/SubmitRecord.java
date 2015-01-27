package info.cormacbrady.rpsrec.data_transfer;

import info.cormacbrady.rpsrec.database.Record;
import info.cormacbrady.rpsrec.database.ReserveDataManager;

import java.net.URL;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubmitRecord {
	private static final String TAG = "MainActivity";
	private static final String URL = "cormacbrady.info/~tkek/add_record.php";

	// private RecordList list;

	public void sendToDatabase(Context context) {

		JSONObject mainObj = new JSONObject();
		JSONObject userObj = new JSONObject();
		String json = "";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(
				"http://cormacbrady.info/~tkek/add_record.php");

		ReserveDataManager dataManager = new ReserveDataManager(context);
		dataManager.open();

		ArrayList<Record> list = new ArrayList<Record>();
		list = dataManager.getAllRecords();
		JSONObject recordJson = null;
		for (int i = 0; i < list.size(); i++) {
			try {
				// add the user information to the json
				userObj.put("name", UserInfo.getName());
				userObj.put("email", UserInfo.getEmail());
				userObj.put("phone", UserInfo.getPhone());
				// looks like this: Latitude:37.422005,Longitude:-122.084095

				// loop through an array of the added records, format them into
				// a
				// json
				// then add that json to the main json
				// this could be done in the addRecord class and then added to
				// the
				// main
				// json here instead

				recordJson = new JSONObject();
				recordJson.put("species", list.get(i).getSpecies());
				recordJson.put("DAFOR",
						Character.toString(list.get(i).getDaforScale()));
				recordJson.put("comments", list.get(i).getAdditionalInfo());
				recordJson.put("date_recorded", list.get(i).getDate());
				recordJson.put("reserve_name", list.get(i).getReserve());
				 recordJson.put("species_photo", list.get(i).getSpeciesPhoto());
				 recordJson.put("locationPhoto", list.get(i).getLocationPhoto());
				recordJson.put("location", list.get(i).getLocation());
				mainObj.put("record", recordJson);

				mainObj.put("user", userObj);
				
				EncodePhoto photo = new EncodePhoto();
				//photo.run(list.get(i).getSpeciesPhoto()))

				System.out.println(mainObj);
				// print the json on the app. PLEASE DELETE!!!
				Log.i(TAG, mainObj.toString(2));
			} catch (JSONException e) {
				e.printStackTrace();
			}

			json = mainObj.toString();
			StringEntity se = null;
			try {
				se = new StringEntity(json);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			httpPost.setEntity(se);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			ResponseHandler responseHandler = new BasicResponseHandler();
			String sendable = null;
			try {
				sendable = httpclient.execute(httpPost, responseHandler);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(sendable);
			// Send the json to the specified URL as a http post
			// HttpClient.sendHttpPost(URL, jsonobj);
		}
		
		dataManager.flushDataBase();
	}
	
	private class EncodePhoto {
		int serverResponseCode;
		 public int uploadFile(String sourceFileUri) {

		        String fileName = sourceFileUri;

		        HttpURLConnection conn = null;
		        DataOutputStream dos = null;  
		        String lineEnd = "\r\n";
		        String twoHyphens = "--";
		        String boundary = "*****";
		        int bytesRead, bytesAvailable, bufferSize;
		        byte[] buffer;
		        int maxBufferSize = 1 * 1024 * 1024; 
		        File sourceFile = new File(sourceFileUri); 

		        if (!sourceFile.isFile()) {
		           
		            Log.e("uploadFile", "Source File not exist :" +sourceFileUri);
		            return 0;
		        }
		        else
		        {
		            try { 

		                // open a URL connection to the Servlet
		                FileInputStream fileInputStream = new FileInputStream(sourceFile);
		                URL url = new URL("http://cormacbrady.info/~tkek/upload_picture.php");

		                // Open a HTTP  connection to  the URL
		                conn = (HttpURLConnection) url.openConnection(); 
		                conn.setDoInput(true); // Allow Inputs
		                conn.setDoOutput(true); // Allow Outputs
		                conn.setUseCaches(false); // Don't use a Cached Copy
		                conn.setRequestMethod("POST");
		                conn.setRequestProperty("Connection", "Keep-Alive");
		                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
		                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
		                conn.setRequestProperty("uploaded_file", fileName); 

		                dos = new DataOutputStream(conn.getOutputStream());

		                dos.writeBytes(twoHyphens + boundary + lineEnd); 
		                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename="+ fileName + "" + lineEnd);
		                dos.writeBytes(lineEnd);

		                // create a buffer of  maximum size
		                bytesAvailable = fileInputStream.available(); 

		                bufferSize = Math.min(bytesAvailable, maxBufferSize);
		                buffer = new byte[bufferSize];

		                // read file and write it into form...
		                bytesRead = fileInputStream.read(buffer, 0, bufferSize);  

		                while (bytesRead > 0) {

		                    dos.write(buffer, 0, bufferSize);
		                    bytesAvailable = fileInputStream.available();
		                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
		                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);   

		                }

		                // send multipart form data necesssary after file data...
		                dos.writeBytes(lineEnd);
		                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

		                // Responses from the server (code and message)
		                serverResponseCode = conn.getResponseCode();
		                String serverResponseMessage = conn.getResponseMessage();

		                
		                Log.i("uploadFile", "HTTP Response is : "+ serverResponseMessage + ": " + serverResponseCode);

		                if(serverResponseCode == 200){

		                    
		                }    

		                //close the streams //
		                fileInputStream.close();
		                dos.flush();
		                dos.close();

		            } catch (MalformedURLException ex) {

		                ex.printStackTrace();
 
		            } catch (Exception e) {
 
		                e.printStackTrace();
		                Log.e("Upload file to server Exception", "Exception : "
		                        + e.getMessage(), e);  
		            }      
		            return serverResponseCode; 

		        } // End else block 
		    } 
	}
}


