package com.tnl.lab07_ex2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ex2Activity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {

    private ListView listView;
    private List<Student> students;
    private MyStudentAdapter adapter;

    private StudentDatabase db;
    private StudentDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backup);
        setTitle("Danh sách sinh viên");

        db = StudentDatabase.getInstance(this);
        dao = db.studentDAO();

        initStudents();

        listView = findViewById(R.id.listView);
        adapter = new MyStudentAdapter(this, R.layout.my_row, students);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        registerForContextMenu(listView);


    } // onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            Log.e("TAG", "No data returned");
            return;
        }
        Student student = data.getParcelableExtra("student");
        if (student == null) {
            Log.e("TAG", "No student returned !");
            return;
        }

        if (student.getId() > 0) {

            dao.update(student);
            int position = data.getIntExtra("position", -1);
            if (position >= 0) {
                students.set(position, student);
                adapter.notifyDataSetChanged();
            }

        } else {
            students.add(0, student);
            adapter.notifyDataSetChanged();

            long id = dao.add(student);

            student.setId(id);
        }
    }

    private void initStudents() {
        students = dao.getAll();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Student student = students.get(position);
        editStudent(student, position);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int position = info.position;

        Student student = students.get(position);
        menu.setHeaderTitle(student.getName());

        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        Student student = students.get(position);

        int id = item.getItemId();
        if (id == R.id.edit) {
            editStudent(student, position);
        }
        else if (id == R.id.remove) {
            removeStudent(student);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, StudentActivity.class);
            startActivityForResult(intent, 1);
        }
        return super.onOptionsItemSelected(item);
    }

    private void removeStudent(Student student) {
        new AlertDialog.Builder(this)
                .setTitle("Xóa sinh viên")
                .setMessage("Bạn có chắc muốn xóa sinh viên " + student.getName() + "?")
                .setPositiveButton("Xóa", (dialog, which) -> {

                    students.remove(student);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Đã xóa sinh viên " + student.getName(),
                            Toast.LENGTH_SHORT).show();

                    dao.delete(student);
                })
                .setNegativeButton("Không", null)
                .create().show();
    }

    private void editStudent(Student student, int position) {
        Intent intent = new Intent(this, StudentActivity.class);

        intent.putExtra("student", student);
        intent.putExtra("position", position);

        startActivityForResult(intent, 1);
    }
}