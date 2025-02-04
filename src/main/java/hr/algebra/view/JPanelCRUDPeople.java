/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.enums.Titles;
import hr.algebra.model.Person;
import hr.algebra.utilities.MessageUtils;
import hr.algebra.view.model.PersonTable;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Paulo
 */
public class JPanelCRUDPeople extends javax.swing.JPanel {
   
 private static final String CRUD_PEOPLE = "CRUD people";
    /**
     * Creates new form JPanel_CRUD_People
     */
    public JPanelCRUDPeople() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbRole = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        cbRole = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        lbState = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPeople = new javax.swing.JTable();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        lbRole.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbRole.setText("Role");

        lbName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbName.setText("Name");

        tfName.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tfNameMouseDragged(evt);
            }
        });
        tfName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfNameMouseClicked(evt);
            }
        });

        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        lbState.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbState.setText("Current situation");

        tbPeople.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPeople.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPeopleMouseClicked(evt);
            }
        });
        tbPeople.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPeopleKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbPeople);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(166, 166, 166)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbState, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(290, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbState, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(btnAdd)
                        .addGap(49, 49, 49)
                        .addComponent(btnDelete)
                        .addGap(57, 57, 57)
                        .addComponent(btnUpdate)
                        .addContainerGap(242, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
         init();
        clearFields();
    }//GEN-LAST:event_formComponentShown

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
     if (!formValid()) {
            return;
        }

        try {
            Person person = new Person(
                    tfName.getText().trim(),
                    Titles.fromString(cbRole.getSelectedItem().toString()));

            int createdPerson = repository.createPerson(person);

            if (createdPerson > 0) {
                MessageUtils.showInformationMessage(CRUD_PEOPLE, "Person has been successfully created!");
            } else {
                MessageUtils.showErrorMessage(CRUD_PEOPLE, "An error occurred while creating person!");
            }

            personTable.setPeople(repository.selectPeople());
            clearFields();

        } catch (Exception ex) {
            Logger.getLogger(JPanelCRUDPeople.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedPerson == null) {
            MessageUtils.
                    showErrorMessage
        (CRUD_PEOPLE, "You have to choose at least one person");
            return;
        }
        if (!formValid()) {
            return;
        }

        deletePerson();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
      if (selectedPerson == null) {
            MessageUtils
          .showErrorMessage
        (CRUD_PEOPLE, "Error, you have to choose a person!");
            return;
        }
        if (!formValid()) {
            return;
        }

        updatePerson();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tbPeopleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPeopleKeyReleased
       showPerson();
    }//GEN-LAST:event_tbPeopleKeyReleased

    private void tbPeopleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPeopleMouseClicked
       showPerson();
    }//GEN-LAST:event_tbPeopleMouseClicked

    private void tfNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNameMouseClicked
     tbPeople.clearSelection();
    }//GEN-LAST:event_tfNameMouseClicked

    private void tfNameMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNameMouseDragged
         tbPeople.clearSelection();
    }//GEN-LAST:event_tfNameMouseDragged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbRole;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbRole;
    private javax.swing.JLabel lbState;
    private javax.swing.JTable tbPeople;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables

    private boolean formValid() {
       if (tfName
               .getText()
               .trim()
               .isEmpty()) {
            MessageUtils
                    .showInformationMessage
        (CRUD_PEOPLE, "Name cannot be empty!");
            return false;
        }

        if (cbRole.getSelectedIndex() == -1) {
            MessageUtils
                    .showInformationMessage
        (CRUD_PEOPLE, "Role cannot be empty!");
            return false;
        }
        return true;
    }
 private Person selectedPerson;
 
    private void clearFields() {
         tfName.setText("");
        cbRole.setSelectedIndex(-1);
        selectedPerson = null;
    }
    private Repository repository;

    private PersonTable personTable;
    private void initRepository() {
        repository = RepositoryFactory.getRepository();
    }

    private void initRoles() {
        cbRole.removeAllItems();
        cbRole.addItem(Titles.DIRECTOR.name());
        cbRole.addItem(Titles.ACTOR.name());
        
    }
    private void initTable() throws Exception {
        tbPeople.setRowHeight(25);
        tbPeople.setAutoCreateRowSorter(true);
        tbPeople.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        personTable = new PersonTable(repository.selectPeople());
        tbPeople.setModel(personTable);
    }

    private void fillForm(Person person) {
        tfName.setText(person.getName());
        cbRole.setSelectedItem(person.getTitle().name());
    }
     private void init() {
       try {
            initRepository();
            initRoles();
            initTable();
        } catch (Exception ex) {
            Logger.getLogger
        (JPanelCRUDPeople.class.getName())
                    .log(Level.SEVERE, null, ex);
            MessageUtils
                    .showErrorMessage
        (CRUD_PEOPLE, "Error initializing form!");
            
            
            System.exit(1);
        }
    }
      private void updatePerson() {
     try {
            selectedPerson.setName(tfName.getText().trim());
            selectedPerson.setTitle(Titles.fromString(cbRole.getSelectedItem().toString()));

            repository.updatePerson(selectedPerson.getId(), selectedPerson);
            personTable.setPeople(repository.selectPeople());

            clearFields();

            MessageUtils
                    .showInformationMessage
        (CRUD_PEOPLE, "Person has been successfully updated!");
        } catch (Exception ex) {
            MessageUtils
                    .showErrorMessage
        (CRUD_PEOPLE, "An error occurred while updating person!");
            Logger.getLogger(JPanelCRUDPeople.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void showPerson() {
       try {
            clearFields();

            int selectedRow = tbPeople.getSelectedRow();
            
            int rowIndex = tbPeople.convertRowIndexToModel(selectedRow);
            
            int id = (int) personTable.getValueAt(rowIndex, 0);

            Optional<Person> optionalPerson = repository.selectPerson(id);
            
            if (optionalPerson.isPresent()) {
                selectedPerson = optionalPerson.get();
                fillForm(selectedPerson);
            }
        } catch (Exception ex) {
            MessageUtils
                    .showErrorMessage
        (CRUD_PEOPLE, "An error has occurred while showing person!");
            Logger.getLogger(JPanelCRUDPeople.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    
       private void deletePerson() {
       try {
            if (MessageUtils
                .showConfirmDialog
        ("Delete person", "Are you sure you want to delete this person?")) {
                repository.deletePerson(selectedPerson.getId());
                personTable.setPeople(repository.selectPeople());

                clearFields();
                MessageUtils
                        .showInformationMessage
        (CRUD_PEOPLE, "Person has been succesfully deleted!");
            }
        } catch (Exception ex) {
            MessageUtils
                    .showErrorMessage
        (CRUD_PEOPLE, "An error occurred while deleting person!");
            Logger.getLogger(JPanelCRUDPeople.class.getName()).log(Level.SEVERE, null, ex);
        }
}

}