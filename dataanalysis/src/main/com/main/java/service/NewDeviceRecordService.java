package main.java.service;

import main.java.dao.MachineNameDAO;
import main.java.dao.NewDeviceRecordDAO;
import main.java.model.NewDeviceMachine;
import main.java.model.NewDeviceRecord;
import main.java.model.NewDeviceStyle;
import main.java.util.Sqldb;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yxy on 9/8/17.
 */
public class NewDeviceRecordService {
    private Date firstDay = new Date(117, 0, 1);

    public static void main(String args[]) {
        NewDeviceRecordService snrs = new NewDeviceRecordService();
        List<NewDeviceMachine> ndmList = snrs.GetMachineByDate(new Date(117, 0, 2), new Date(117, 0, 7));
        for (int i = 0; i < ndmList.size(); i++) {
            System.out.println(ndmList.get(i));
        }
        List<NewDeviceStyle> ndsList = snrs.GetStyleByDateMachine(new Date(117, 0, 1), new Date(117, 0, 1), "小米");
        for (int i = 0; i < ndsList.size(); i++) {
            System.out.println(ndsList.get(i));
        }
    }

    public List<NewDeviceMachine> GetMachineByDate(Date date1, Date date2) {
        Connection conn = null;
        List<NewDeviceMachine> ndmList = new ArrayList<>();
        try {
            conn = Sqldb.getDefaultConnection();
            List<String> machineList = MachineNameDAO.GetMachineName(conn);
            for (int i = 0; i < machineList.size(); i++) {
                NewDeviceMachine ndm = new NewDeviceMachine();
                ndm.setMachinename(machineList.get(i));
                List<NewDeviceRecord> ndrList = NewDeviceRecordDAO.SelectByDateAndMachine(conn, new java.sql.Date(date1.getTime()),
                        new java.sql.Date(date2.getTime()), machineList.get(i).substring(0,machineList.get(i).length()-1));
                int newnum = 0;
                for (int j = 0; j < ndrList.size(); j++) {
                    newnum += ndrList.get(j).getNumber();
                }
                ndm.setNewnum(newnum);
                List<NewDeviceRecord> ndrList2 = NewDeviceRecordDAO.SelectByDateAndMachine(conn, new java.sql.Date(firstDay.getTime()),
                        new java.sql.Date(date1.getTime()), machineList.get(i).substring(0,machineList.get(i).length()-1));
                int oldnum = 0;
                for (int j = 0; j < ndrList2.size(); j++) {
                    oldnum += ndrList2.get(j).getNumber();
                }
                ndm.setOldnum(oldnum);
                ndm.setAllnum(newnum + oldnum);
                ndmList.add(ndm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Sqldb.closeConnection(conn);
        }
        return ndmList;
    }

    public List<NewDeviceStyle> GetStyleByDateMachine(Date date1, Date date2, String machinename) {
        Connection conn = null;
        List<NewDeviceStyle> ndsList = new ArrayList<>();
        try {
            List<NewDeviceRecord> ndrList = NewDeviceRecordDAO.SelectByDateAndMachine(conn, new java.sql.Date(date1.getTime()),
                    new java.sql.Date(date2.getTime()), machinename);
            for (int i = 0; i < ndrList.size(); i++) {
                NewDeviceStyle nds = new NewDeviceStyle();
                List<NewDeviceRecord> ndrList2 = NewDeviceRecordDAO.SelectByDateAndStyle(conn, new java.sql.Date(firstDay.getTime()),
                        new java.sql.Date(date1.getTime()), ndrList.get(i).getStyle());
                int newnum = 0;
                for (int j = 0; j < ndrList2.size(); j++) {
                    newnum += ndrList2.get(j).getNumber();
                }
                List<NewDeviceRecord> ndrList3 = NewDeviceRecordDAO.SelectByDateAndStyle(conn, new java.sql.Date(firstDay.getTime()),
                        new java.sql.Date(date1.getTime()), ndrList.get(i).getStyle());
                int oldnum = 0;
                for (int j = 0; j < ndrList3.size(); j++) {
                    oldnum += ndrList3.get(j).getNumber();
                }
                nds.setNewDeviceStyle(ndrList.get(i).getStyle().substring(machinename.length()), oldnum, newnum, oldnum + newnum);
                ndsList.add(nds);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Sqldb.closeConnection(conn);
        }
        return ndsList;
    }

}
