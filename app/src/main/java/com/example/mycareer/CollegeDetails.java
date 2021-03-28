package com.example.mycareer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class CollegeDetails extends AppCompatActivity {

    private static final String FIRE_LOG = "Fire_log";
    private boolean table_flag = false;
    TextView CName,CAddress,CDetails,CLink,Row1Branch,Row2Branch,Row1Fee,Row2Fee,Row1Placement,Row2Placement,Row3Branch,Row3Fee,Row3Placement;
    FirebaseFirestore firebaseFirestore;
    ImageSlider imageSlider;
    Button tablebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_details);
        Row1Branch=(TextView)findViewById(R.id.row1branch);
        Row2Branch=(TextView)findViewById(R.id.row2branch);
        Row3Branch=(TextView)findViewById(R.id.row3branch);
        Row1Fee=(TextView)findViewById(R.id.row1fee);
        Row2Fee=(TextView)findViewById(R.id.row2fee);
        Row3Fee=(TextView)findViewById(R.id.row3fee);
        Row1Placement=(TextView)findViewById(R.id.row1placement);
        Row2Placement=(TextView)findViewById(R.id.row2placement);
        Row3Placement=(TextView)findViewById(R.id.row3placement);
        CName=(TextView)findViewById(R.id.cName);
        CAddress=(TextView)findViewById(R.id.cAddress);
        CDetails=(TextView)findViewById(R.id.cDetails);
        CLink=(TextView)findViewById(R.id.cLink);
        tablebtn=(Button)findViewById(R.id.tablebutton);
        imageSlider=findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();

        firebaseFirestore=FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        String charusat = "Charusat University";
        String nirma = "Nirma University";
        String pdpu = "PDPU University";
        String ld = "LD College of Engineering";
        String msu = "Maharaja Sahajirav University";
        String iit = "Indian Institute of Technology";
        String svnit = "Sardar Vallabhbhai National Institute of Technology";
        String daiict = "Dhirubhai Ambani Institute of Information and Communication Technology";
        String ddu = "Dharmsinh Desai University";
        String str1="Branch1",str2="Branch2",str3="Branch3";
        if(bundle!=null){
            CName.setText(bundle.getString("Name"));
            Toast.makeText(CollegeDetails.this, bundle.getString("Name"), Toast.LENGTH_SHORT).show();
            if(charusat.equals(CName.getText().toString()))
            {
                String clg="Charusat";
                databasefetch(clg);
                tablefetch(clg,str1,str2,str3);
                slideModels.add(new SlideModel("https://cdn.collegebol.com/media/college_header_image/charotar-university-of-science-and-technology-charusat-vallabh-vidhyanagar.jpg",""));
                slideModels.add(new SlideModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcharusat.ac.in%2Fadmission%2Fimages%2Fhome_slider%2F3.jpg&f=1&nofb=1",""));
                slideModels.add(new SlideModel("https://contactinformation.in/wp-content/uploads/2017/04/charotar_university11.jpg",""));
                slideModels.add(new SlideModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.fresherslive.com%2Fassets-images%2Feducation%2Forigin%2F2020%2F07%2F15%2Fcharusat-result.jpg&f=1&nofb=1",""));
                imageSlider.setImageList(slideModels,true);
            }
            else if (nirma.equals(CName.getText().toString()))
            {
                String clg="Nirma";
                databasefetch(clg);
                tablefetch(clg,str1,str2,str3);
                slideModels.add(new SlideModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Ftechnology.nirmauni.ac.in%2Fwp-content%2Fuploads%2Fsites%2F22%2F2019%2F01%2F1920x1285.jpg&f=1&nofb=1",""));
                slideModels.add(new SlideModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.uWR12aVls_fvbzaPxKOz1wHaFj%26pid%3DApi&f=1",""));
                slideModels.add(new SlideModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.6kltpi9XqrmlxgecVDEmlAHaE8%26pid%3DApi&f=1",""));
                slideModels.add(new SlideModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.RVH1uW6mTIK3nugs7eXFaAHaFj%26pid%3DApi&f=1",""));
                imageSlider.setImageList(slideModels,true);
            }
            else if (pdpu.equals(CName.getText().toString()))
            {
                String clg="PDPU";
                databasefetch(clg);
                tablefetch(clg,str1,str2,str3);
                slideModels.add(new SlideModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2F8%2F82%2FPDPU_campus_4.png%2F550px-PDPU_campus_4.png&f=1&nofb=1",""));
                slideModels.add(new SlideModel("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fpdpu.ac.in%2Fevents%2F17%2F17.jpg&f=1&nofb=1",""));
                slideModels.add(new SlideModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.ElK0Hv8PJ_jzwry3NqBdKwHaE4%26pid%3DApi&f=1",""));
                slideModels.add(new SlideModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.UmH1VtTtfqHXhq6N6-EN0gHaEK%26pid%3DApi&f=1",""));
                imageSlider.setImageList(slideModels,true);

            }
            else if (ld.equals(CName.getText().toString()))
            {
                String clg="LD";
                databasefetch(clg);
                tablefetch(clg,str1,str2,str3);
                slideModels.add(new SlideModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.A4jiW5PRzJiK61zlmQlCHwHaFj%26pid%3DApi&f=1",""));
                slideModels.add(new SlideModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.vhmmESMpU2ClV_Iz3jkS9AAAAA%26pid%3DApi&f=1",""));
                slideModels.add(new SlideModel("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.b5UzQq7-627_hryHvTqvKgHaFK%26pid%3DApi&f=1",""));
                imageSlider.setImageList(slideModels,true);
            }
            else if (msu.equals(CName.getText().toString()))
            {
                String clg="MSU";
                databasefetch(clg);
                tablefetch(clg,str1,str2,str3);
                slideModels.add(new SlideModel("https://i.ytimg.com/vi/hhx5cNI_7N8/hqdefault.jpg",""));
                slideModels.add(new SlideModel("https://www.msubaroda.ac.in/asset/storage/pages/thumb_icon1393817968.jpeg",""));
                slideModels.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRVuyweT2M9dJ1IlsFdze_7piU3N9JXVji4RA&usqp=CAU",""));
                imageSlider.setImageList(slideModels,true);
            }
            else if (iit.equals(CName.getText().toString()))
            {
                String clg="IIT";
                databasefetch(clg);
                tablefetch(clg,str1,str2,str3);
                slideModels.add(new SlideModel("https://www.pagalguy.com/wp-content/uploads/2020/01/IIT-GN-PIC.-1.jpg",""));
                slideModels.add(new SlideModel("https://cdn.dnaindia.com/sites/default/files/styles/full/public/2019/02/12/790072-iitgn-iit-gandhinagar-021219.jpg",""));
                slideModels.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSbEJN5AIuM7EOMOBghG5_LpBdmYxk9y7O9GA&usqp=CAU",""));
                imageSlider.setImageList(slideModels,true);
            }
            else if (svnit.equals(CName.getText().toString()))
            {
                String clg="SVNIT";
                databasefetch(clg);
                tablefetch(clg,str1,str2,str3);
                slideModels.add(new SlideModel("https://static.toiimg.com/photo/69117120.cms",""));
                slideModels.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQgPLPIEXoKsuMJ19qP8PeHgzMU9O0fQv0xZA&usqp=CAU",""));
                slideModels.add(new SlideModel("https://www.instituteshub.com/wp-content/uploads/2019/06/SVNIT.jpg",""));
                imageSlider.setImageList(slideModels,true);
            }
            else if (daiict.equals(CName.getText().toString()))
            {
                String clg="DAIICT";
                databasefetch(clg);
                tablefetch(clg,str1,str2,str3);
                slideModels.add(new SlideModel("https://images.shiksha.com/mediadata/images/1492513341php1FIK5E.jpeg",""));
                slideModels.add(new SlideModel("https://ahmedabadmirror.indiatimes.com/thumb/66994283.cms?resizemode=4&width=400",""));
                slideModels.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTOjM74Sg_-iEudHbr8Cl3liD_u8BBWzPPUAg&usqp=CAU",""));
                imageSlider.setImageList(slideModels,true);
            }
            else if(ddu.equals(CName.getText().toString()))
            {
                String clg = "DDU";
                databasefetch(clg);
                tablefetch(clg,str1,str2,str3);
                slideModels.add(new SlideModel("https://www.ddu.ac.in/portfolio2/large/1.jpg",""));
                slideModels.add(new SlideModel("https://images.collegedunia.com/public/college_data/images/appImage/25483_dharmsinh_new.jpg",""));
                slideModels.add(new SlideModel("https://www.eduvidya.com/admin/Upload/Institutes/635131148366982703_dharmish.JPG",""));
                slideModels.add(new SlideModel("https://www.edufever.com/wp-content/uploads/2018/04/Untitled-min-4-1.jpg",""));
                imageSlider.setImageList(slideModels,true);
            }
        }
        tablefunction();
    }



    public void tablefetch(String string,String s1,String s2,String s3){
        branch1(string,s1);
        branch2(string,s2);
        branch3(string,s3);
    }

    public void branch1(String string,String s){
        firebaseFirestore.collection("colleges").document(string).collection("Branches").document(s).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    String branch=documentSnapshot.getString("Branch");
                    String fee=documentSnapshot.getString("Fee");
                    String placement=documentSnapshot.getString("Placement");
                    Row1Branch.setText(branch);
                    Row1Fee.setText(fee);
                    Row1Placement.setText(placement);
                }
                else
                    Log.d(FIRE_LOG,"Error :"+task.getException().getMessage());
            }
        });
    }
    public void branch2(String string,String s){
        firebaseFirestore.collection("colleges").document(string).collection("Branches").document(s).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    String branch=documentSnapshot.getString("Branch");
                    String fee=documentSnapshot.getString("Fee");
                    String placement=documentSnapshot.getString("Placement");
                    Row2Branch.setText(branch);
                    Row2Fee.setText(fee);
                    Row2Placement.setText(placement);
                }
                else
                    Log.d(FIRE_LOG,"Error :"+task.getException().getMessage());
            }
        });
    }
    public void branch3(String string,String s){
        firebaseFirestore.collection("colleges").document(string).collection("Branches").document(s).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    String branch=documentSnapshot.getString("Branch");
                    String fee=documentSnapshot.getString("Fee");
                    String placement=documentSnapshot.getString("Placement");
                    Row3Branch.setText(branch);
                    Row3Fee.setText(fee);
                    Row3Placement.setText(placement);
                }
                else
                    Log.d(FIRE_LOG,"Error :"+task.getException().getMessage());
            }
        });
    }




    public void databasefetch(final String string){
        firebaseFirestore.collection("colleges").document(string).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    String address=documentSnapshot.getString("Address");
                    CAddress.setText("\n"+address);
                    String details=documentSnapshot.getString("Details");
                    CDetails.setText("\n"+details);
                    String link=documentSnapshot.getString("Link");
                    if(string.equals("Charusat")){
                        CLink.setMovementMethod(LinkMovementMethod.getInstance());
                        CLink.setText(R.string.charusat);
                    }
                    else if(string.equals("Nirma")){
                        CLink.setMovementMethod(LinkMovementMethod.getInstance());
                        CLink.setText(R.string.nirma);
                    }
                    else if(string.equals("MSU")){
                        CLink.setMovementMethod(LinkMovementMethod.getInstance());
                        CLink.setText(R.string.msu);
                    }
                    else if(string.equals("LD")){
                        CLink.setMovementMethod(LinkMovementMethod.getInstance());
                        CLink.setText(R.string.ld);
                    }
                    else if(string.equals("IIT")){
                        CLink.setMovementMethod(LinkMovementMethod.getInstance());
                        CLink.setText(R.string.iit);
                    }
                    else if(string.equals("SVNIT")){
                        CLink.setMovementMethod(LinkMovementMethod.getInstance());
                        CLink.setText(R.string.svnit);
                    }
                    else if(string.equals("PDPU")){
                        CLink.setMovementMethod(LinkMovementMethod.getInstance());
                        CLink.setText(R.string.pdpu);
                    }
                    else if(string.equals("DAIICT")){
                        CLink.setMovementMethod(LinkMovementMethod.getInstance());
                        CLink.setText(R.string.daiict);
                    }
                    else if(string.equals("DDU")){
                        CLink.setMovementMethod(LinkMovementMethod.getInstance());
                        CLink.setText(R.string.ddu);
                    }
                }
                else {
                    Log.d(FIRE_LOG,"Error : "+ task.getException().getMessage());
                }
            }
        });
    }



    public void tablefunction()
    {
        tablebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableLayout tableLayout = findViewById(R.id.table);
                tableLayout.setColumnCollapsed(1,table_flag);
                tableLayout.setColumnCollapsed(2,table_flag);

                if(table_flag){
                    table_flag = false;
                    tablebtn.setText("Show Details");
                }
                else {
                    table_flag = true;
                    tablebtn.setText("Hide Details");
                }
            }
        });
    }

}