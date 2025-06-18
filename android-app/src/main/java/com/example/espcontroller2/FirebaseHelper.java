package com.example.espcontroller2;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private final DatabaseReference databaseReference;

    public FirebaseHelper() {
        databaseReference = FirebaseDatabase.getInstance().getReference("carControl");
    }

    public void setControlStatus(boolean status, String userId) {
        databaseReference.child("isCarInUse").setValue(status);
        databaseReference.child("controllerUser").setValue(status ? userId : "");
    }

    public DatabaseReference getControlStatus() {
        return databaseReference;
    }
}
