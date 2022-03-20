package ma.emsi.second_app.service;

import java.util.ArrayList;
import java.util.List;

import ma.emsi.second_app.classes.Doctor;
import ma.emsi.second_app.dao.IDao;

public class DoctorService implements IDao<Doctor> {

    private List<Doctor> doctors;
    private static DoctorService instance;

    public DoctorService() {
        this.doctors = new ArrayList<>();
    }

    public static DoctorService getInstance() {
        if (instance == null)
            instance=new DoctorService();
        return instance;
    }

    @Override
    public boolean create(Doctor o) {
        return doctors.add(o);
    }

    @Override
    public boolean update(Doctor o) {
        for (Doctor d: doctors) {
            if(o.getId() == d.getId()){
                d.setImg(o.getImg());
                d.setName(o.getName());
                d.setStar(o.getStar());
            }
        }
        return true ;
    }

    @Override
    public boolean delete(Doctor o) {
        return doctors.remove(o);
    }

    @Override
    public List<Doctor> findAll() {
        return doctors;
    }

    @Override
    public Doctor findById(int id) {
        for(Doctor d :doctors) {
            if (d.getId() == id)
                return d;
        }
        return null ;
    }
}
