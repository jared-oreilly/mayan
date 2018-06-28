/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oreilly;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author jared.oreilly
 */
public class GraphUI extends javax.swing.JFrame
{

    Graph m;

    /**
     * Creates new form GraphUI
     */
    public GraphUI()
    {
        m = new Graph("http://tagsuatappservice.azurewebsites.net");
        initComponents();
        updateDisplays();
        //System.out.println(m);

        //new DiagramUI(m).setVisible(true);
    }

    public GraphUI(String filename)
    {
        m = new Graph("");
        m.importGraph(filename);
        initComponents();
        updateDisplays();
        txfFilename.setText(filename.substring(0, filename.length() - 5));
        txfMayan.setText(filename.substring(0, filename.length() - 5));
        //System.out.println(m);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lblAddNode = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblURL = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        lblCookie = new javax.swing.JLabel();
        lblJSON = new javax.swing.JLabel();
        lblForm = new javax.swing.JLabel();
        txfCookie = new javax.swing.JTextField();
        txfJSON = new javax.swing.JTextField();
        txfURL = new javax.swing.JTextField();
        txfTitleNode = new javax.swing.JTextField();
        txfForm = new javax.swing.JTextField();
        btnAddNode = new javax.swing.JButton();
        lblProb = new javax.swing.JLabel();
        txfStart = new javax.swing.JTextField();
        txfProb = new javax.swing.JTextField();
        txfTitleEdge = new javax.swing.JTextField();
        txfEnd = new javax.swing.JTextField();
        btnAddEdge = new javax.swing.JButton();
        lblAddAction = new javax.swing.JLabel();
        lblTitleAction = new javax.swing.JLabel();
        lblStartID = new javax.swing.JLabel();
        lblEndID = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaEdges = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaNodes = new javax.swing.JTextArea();
        btnMA = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        lbljson = new javax.swing.JLabel();
        txfFilename = new javax.swing.JTextField();
        txfMayan = new javax.swing.JTextField();
        lbltxt = new javax.swing.JLabel();
        cbxType = new javax.swing.JComboBox<>();
        lblTitleFeedback = new javax.swing.JLabel();
        lblURLFeedback = new javax.swing.JLabel();
        lblCookieFeedback = new javax.swing.JLabel();
        lblJSONFeedback = new javax.swing.JLabel();
        lblFormFeedback = new javax.swing.JLabel();
        lblTitleActionFeedback = new javax.swing.JLabel();
        lblProbFeedback = new javax.swing.JLabel();
        lblStartIDFeedback = new javax.swing.JLabel();
        lblEndIDFeedback = new javax.swing.JLabel();
        lblTypeFeedback = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mayan - Create Graph");
        setBackground(new java.awt.Color(0, 204, 0));
        setForeground(new java.awt.Color(0, 204, 0));

        lblAddNode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAddNode.setText("Add Webpage/Request");

        lblTitle.setText("Title");

        lblURL.setText("URL");

        lblType.setText("Type ");

        lblCookie.setText("Cookie");

        lblJSON.setText("JSON");

        lblForm.setText("Form ");

        btnAddNode.setText("Add");
        btnAddNode.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAddNodeActionPerformed(evt);
            }
        });

        lblProb.setText("Prob ");

        btnAddEdge.setText("Add");
        btnAddEdge.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAddEdgeActionPerformed(evt);
            }
        });

        lblAddAction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAddAction.setText("Add Action/Link");

        lblTitleAction.setText("Title");

        lblStartID.setText("StartID");

        lblEndID.setText("EndID");

        txaEdges.setColumns(20);
        txaEdges.setRows(5);
        jScrollPane1.setViewportView(txaEdges);

        txaNodes.setColumns(20);
        txaNodes.setRows(5);
        jScrollPane2.setViewportView(txaNodes);

        btnMA.setText("Mayan Artillery");
        btnMA.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnMAActionPerformed(evt);
            }
        });

        btnExport.setText("Export Graph");
        btnExport.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnExportActionPerformed(evt);
            }
        });

        lbljson.setText(".json");

        lbltxt.setText(".txt");

        cbxType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GET", "POST" }));
        cbxType.setPreferredSize(new java.awt.Dimension(6, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblForm)
                            .addComponent(lblJSON)
                            .addComponent(lblCookie)
                            .addComponent(lblTitle)
                            .addComponent(lblURL)
                            .addComponent(lblType))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txfURL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(cbxType, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txfJSON, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txfForm, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txfCookie, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txfTitleNode))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTitleFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFormFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblJSONFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                        .addComponent(lblURLFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblCookieFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblTypeFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(btnAddNode, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAddNode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProb)
                                    .addComponent(lblEndID)
                                    .addComponent(lblStartID)
                                    .addComponent(lblTitleAction))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txfTitleEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txfProb)
                                        .addComponent(txfEnd, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txfStart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTitleActionFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblStartIDFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblProbFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblEndIDFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblAddAction, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                        .addGap(69, 69, 69))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txfFilename)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbljson)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txfMayan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbltxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAddNode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTitle)
                                    .addComponent(txfTitleNode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTitleFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblURL)
                                    .addComponent(txfURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblURLFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblType)
                                        .addComponent(cbxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblTypeFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCookieFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCookie)
                                        .addComponent(txfCookie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblJSON)
                                        .addComponent(txfJSON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblJSONFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFormFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblForm)
                                        .addComponent(txfForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAddNode)
                                    .addComponent(btnAddEdge))
                                .addGap(17, 17, 17))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTitleAction)
                                        .addComponent(txfTitleEdge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblTitleActionFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblStartID)
                                        .addComponent(txfStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblStartIDFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblEndIDFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblEndID)
                                        .addComponent(txfEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblProb)
                                        .addComponent(txfProb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblProbFeedback, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMA)
                            .addComponent(btnExport)
                            .addComponent(lbljson)
                            .addComponent(txfFilename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfMayan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltxt)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAddAction)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddNodeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAddNodeActionPerformed
    {//GEN-HEADEREND:event_btnAddNodeActionPerformed
        //do validation on node fields
        boolean submit = true;
        //txfTitleNode
        String t = txfTitleNode.getText().trim();
        String ty = (cbxType.getSelectedItem() + "");
        if (t.equals(""))
        {
            lblTitleFeedback.setText("Required!");
            submit = false;
        } else
        {
            lblTitleFeedback.setText("");
            lblTypeFeedback.setText("");
        }
        //txfURL
        if (txfURL.getText().trim().equals(""))
        {
            lblURLFeedback.setText("Required!");
            submit = false;
        } else
        {
            lblURLFeedback.setText("");
        }
        //txfCookie
        //txfJSON
        //txfForm

        if (submit && m.nodeTaken(t, ty))
        {
            lblTitleFeedback.setText("Taken!");
            lblTypeFeedback.setText("Taken!");
            submit = false;
        } 
        
        
        if (submit)
        {
            if ((cbxType.getSelectedItem() + "").equals("POST"))
            {
                String title = txfTitleNode.getText();
                String URL = txfURL.getText();
                String type = (cbxType.getSelectedItem() + "");
                String cookie = txfCookie.getText();
                String JSON = txfJSON.getText();
                if (JSON.equals(""))
                {
                    JSON = null;
                }
                String form = txfForm.getText();
                if (form.equals(""))
                {
                    form = null;
                }
                int id = m.addNode(title, URL, type, cookie, JSON, form);
            } else if ((cbxType.getSelectedItem() + "").equals("GET"))
            {
                String title = txfTitleNode.getText();
                String URL = txfURL.getText();
                String type = (cbxType.getSelectedItem() + "");
                String cookie = txfCookie.getText();
                int id = m.addNode(title, URL, type, cookie);
            }
            updateDisplays();
        } else
        {

        }
    }//GEN-LAST:event_btnAddNodeActionPerformed

    private void btnAddEdgeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAddEdgeActionPerformed
    {//GEN-HEADEREND:event_btnAddEdgeActionPerformed
        //do validation on edge fields
        boolean submit = true;
        String t = txfTitleEdge.getText().trim();
        String s = txfStart.getText().trim();
        String e = txfEnd.getText().trim();
        String p = txfProb.getText().trim();
        //Title
        if (t.equals(""))
        {
            lblTitleActionFeedback.setText("Required!");
            submit = false;
        } else
        {
            lblTitleActionFeedback.setText("");
        }
        //StartID
        if (s.equals(""))
        {
            lblStartIDFeedback.setText("Required!");
            submit = false;
        } else
        {
            lblStartIDFeedback.setText("");
        }
        //EndID
        if (e.equals(""))
        {
            lblEndIDFeedback.setText("Required!");
            submit = false;
        } else
        {
            lblEndIDFeedback.setText("");
        }
        //Prob
        if (p.equals(""))
        {
            lblProbFeedback.setText("Required!");
            submit = false;
        } else
        {
            lblProbFeedback.setText("");
        }
        
        if (submit && m.edgeTaken(t, s, e))
        {
            lblTitleActionFeedback.setText("Taken!");
            lblStartIDFeedback.setText("Taken!");
            lblEndIDFeedback.setText("Taken!");
            submit = false;
        } 

        if (submit)
        {
            String title = txfTitleEdge.getText();
            int startID = Integer.parseInt(txfStart.getText());
            int endID = Integer.parseInt(txfEnd.getText());
            double prob = Double.parseDouble(txfProb.getText());
            int id = m.addEdge(title, startID, endID, prob);
            updateDisplays();
        } else
        {

        }
    }//GEN-LAST:event_btnAddEdgeActionPerformed

    private void btnMAActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnMAActionPerformed
    {//GEN-HEADEREND:event_btnMAActionPerformed
        m.mayanArtillery(txfMayan.getText() + ".txt");
        JOptionPane.showMessageDialog(null, txfMayan.getText() + ".txt generated!");
    }//GEN-LAST:event_btnMAActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnExportActionPerformed
    {//GEN-HEADEREND:event_btnExportActionPerformed
        m.exportGraph(txfFilename.getText() + ".json");
        JOptionPane.showMessageDialog(null, txfFilename.getText() + ".json exported!");
    }//GEN-LAST:event_btnExportActionPerformed

    private void updateDisplays()
    {
        updateNodesDisplay();
        updateEdgesDisplay();
    }

    private void updateNodesDisplay()
    {
        txaNodes.setText(m.printNodes());
    }

    private void updateEdgesDisplay()
    {
        txaEdges.setText(m.printEdges());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(GraphUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(GraphUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(GraphUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(GraphUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new GraphUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddEdge;
    private javax.swing.JButton btnAddNode;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnMA;
    private javax.swing.JComboBox<String> cbxType;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAddAction;
    private javax.swing.JLabel lblAddNode;
    private javax.swing.JLabel lblCookie;
    private javax.swing.JLabel lblCookieFeedback;
    private javax.swing.JLabel lblEndID;
    private javax.swing.JLabel lblEndIDFeedback;
    private javax.swing.JLabel lblForm;
    private javax.swing.JLabel lblFormFeedback;
    private javax.swing.JLabel lblJSON;
    private javax.swing.JLabel lblJSONFeedback;
    private javax.swing.JLabel lblProb;
    private javax.swing.JLabel lblProbFeedback;
    private javax.swing.JLabel lblStartID;
    private javax.swing.JLabel lblStartIDFeedback;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleAction;
    private javax.swing.JLabel lblTitleActionFeedback;
    private javax.swing.JLabel lblTitleFeedback;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel lblTypeFeedback;
    private javax.swing.JLabel lblURL;
    private javax.swing.JLabel lblURLFeedback;
    private javax.swing.JLabel lbljson;
    private javax.swing.JLabel lbltxt;
    private javax.swing.JTextArea txaEdges;
    private javax.swing.JTextArea txaNodes;
    private javax.swing.JTextField txfCookie;
    private javax.swing.JTextField txfEnd;
    private javax.swing.JTextField txfFilename;
    private javax.swing.JTextField txfForm;
    private javax.swing.JTextField txfJSON;
    private javax.swing.JTextField txfMayan;
    private javax.swing.JTextField txfProb;
    private javax.swing.JTextField txfStart;
    private javax.swing.JTextField txfTitleEdge;
    private javax.swing.JTextField txfTitleNode;
    private javax.swing.JTextField txfURL;
    // End of variables declaration//GEN-END:variables
}
