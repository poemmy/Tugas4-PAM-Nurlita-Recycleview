package com.example.tugas4_pam_recycleview;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private ArrayList<Contact> contactList;
    private ArrayList<Contact> pendingContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi daftar kontak
        contactList = new ArrayList<>();
        pendingContacts = new ArrayList<>();

        // Menginisialisasi RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Menambahkan data dummy ke list kontak
        initializeDummyContacts();

        // Menginisialisasi adapter dan menghubungkannya dengan RecyclerView
        contactAdapter = new ContactAdapter(contactList);
        recyclerView.setAdapter(contactAdapter);

        // Menghubungkan tombol FAB untuk menambahkan kontak
        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> showAddContactDialog());

        // Menghubungkan tombol refresh
        FloatingActionButton refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(v -> {
            // Transfer pending contacts to visible contact list and refresh the adapter
            contactList.addAll(pendingContacts);
            pendingContacts.clear();
            contactAdapter.notifyDataSetChanged();
        });
    }

    // Metode untuk menambahkan data dummy
    private void initializeDummyContacts() {
        contactList.add(new Contact("Nurlita", "225150407111044", R.drawable.ic_default_profile));
        contactList.add(new Contact("Azizah", "987654321", R.drawable.ic_default_profile));
        contactList.add(new Contact("Lita", "456123789", R.drawable.ic_default_profile));
    }

    // Metode untuk menampilkan dialog penambahan kontak
    private void showAddContactDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_contact, null);
        builder.setView(dialogView);

        final EditText inputName = dialogView.findViewById(R.id.inputName);
        final EditText inputPhone = dialogView.findViewById(R.id.inputPhone);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String name = inputName.getText().toString();
            String phone = inputPhone.getText().toString();

            if (!name.isEmpty() && !phone.isEmpty()) {
                // Tambahkan kontak ke pending list
                pendingContacts.add(new Contact(name, phone, R.drawable.ic_default_profile)); // Menambahkan gambar profil default
                contactAdapter.notifyDataSetChanged(); // Memberitahu adapter untuk memperbarui tampilan
                dialog.dismiss(); // Tutup dialog
            } else {
                Toast.makeText(this, "Name and phone cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }
}
