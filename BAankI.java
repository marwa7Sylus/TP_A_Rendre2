package metier;

import java.awt.*;
import java.awt.event.*;

public class BAankI extends Frame {
    // Components
    private Label titleLabel, codeLabel, soldeLabel, operationLabel, montantLabel, resultLabel;
    private TextField codeField, soldeField, montantField;
    private Button createNormalBtn, createSavingsBtn, createPayingBtn;
    private Button verserBtn, retirerBtn, displayBtn, calculateInterestBtn;
    private Choice accountTypeChoice;
    private Panel topPanel, centerPanel, bottomPanel;
    private Comptes currentAccount;

    public BAankI() {
        setTitle("Système Bancaire");
        setSize(600, 500);
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 240, 240));
        initializeComponents();
        setupPanels();
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setupEventHandlers();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
        setVisible(true);
    }
    private void initializeComponents() {
        titleLabel = new Label("Gestion des Comptes Bancaires", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        codeLabel = new Label("Code:");
        soldeLabel = new Label("Solde initial:");
        operationLabel = new Label("Choisir opération:");
        montantLabel = new Label("Montant:");
        resultLabel = new Label("");
        resultLabel.setForeground(Color.BLUE);
        codeField = new TextField(10);
        soldeField = new TextField(10);
        montantField = new TextField(10);
        createNormalBtn = new Button("Créer Compte Simple");
        createSavingsBtn = new Button("Créer Compte Épargne");
        createPayingBtn = new Button("Créer Compte Payant");
        verserBtn = new Button("Verser");
        retirerBtn = new Button("Retirer");
        displayBtn = new Button("Afficher Détails");
        calculateInterestBtn = new Button("Calculer Intérêt");
        accountTypeChoice = new Choice();
        accountTypeChoice.add("Compte Simple");
        accountTypeChoice.add("Compte Épargne");
        accountTypeChoice.add("Compte Payant");
    }
    private void setupPanels() {
        topPanel = new Panel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.CENTER);
        centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(8, 2, 10, 10));
        centerPanel.add(codeLabel);
        centerPanel.add(codeField);
        centerPanel.add(soldeLabel);
        centerPanel.add(soldeField);
        centerPanel.add(new Label("Type de compte:"));
        centerPanel.add(accountTypeChoice);
        Panel createButtonsPanel = new Panel();
        createButtonsPanel.setLayout(new FlowLayout());
        createButtonsPanel.add(createNormalBtn);
        createButtonsPanel.add(createSavingsBtn);
        createButtonsPanel.add(createPayingBtn);
        centerPanel.add(new Label(""));
        centerPanel.add(createButtonsPanel);
        centerPanel.add(operationLabel);
        Panel operationButtonsPanel = new Panel();
        operationButtonsPanel.setLayout(new FlowLayout());
        operationButtonsPanel.add(verserBtn);
        operationButtonsPanel.add(retirerBtn);
        operationButtonsPanel.add(displayBtn);
        operationButtonsPanel.add(calculateInterestBtn);
        centerPanel.add(operationButtonsPanel);
        centerPanel.add(montantLabel);
        centerPanel.add(montantField);
        bottomPanel = new Panel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(resultLabel, BorderLayout.CENTER);
    }
    private void setupEventHandlers() {
        createNormalBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int code = Integer.parseInt(codeField.getText());
                    float solde = Float.parseFloat(soldeField.getText());
                    currentAccount = new Comptesimple(code, solde);
                    resultLabel.setText("Compte Simple créé avec succès. Code: " + code);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Erreur: Veuillez entrer des valeurs numériques valides.");
                }
            }
        });

        createSavingsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int code = Integer.parseInt(codeField.getText());
                    float solde = Float.parseFloat(soldeField.getText());
                    float tauxInteret = 2.5f; // Default interest rate
                    currentAccount = new Compteepargne(code, solde, tauxInteret);
                    resultLabel.setText("Compte Épargne créé avec succès. Code: " + code + ", Taux d'intérêt: 2.5%");
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Erreur: Veuillez entrer des valeurs numériques valides.");
                }
            }
        });

        createPayingBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int code = Integer.parseInt(codeField.getText());
                    float solde = Float.parseFloat(soldeField.getText());
                    currentAccount = new Comptepayant(code, solde);
                    resultLabel.setText("Compte Payant créé avec succès. Code: " + code);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Erreur: Veuillez entrer des valeurs numériques valides.");
                }
            }
        });

        // Operation buttons
        verserBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentAccount == null) {
                    resultLabel.setText("Erreur: Veuillez d'abord créer un compte.");
                    return;
                }

                try {
                    float montant = Float.parseFloat(montantField.getText());
                    if (currentAccount instanceof Comptepayant) {
                        ((Comptepayant) currentAccount).verser(montant);
                        resultLabel.setText("Versement effectué avec succès sur le compte payant.");
                    } else {
                        // For other account types, we'd need to implement the verser method
                        resultLabel.setText("Opération non supportée pour ce type de compte.");
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Erreur: Veuillez entrer un montant valide.");
                }
            }
        });

        retirerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentAccount == null) {
                    resultLabel.setText("Erreur: Veuillez d'abord créer un compte.");
                    return;
                }

                try {
                    float montant = Float.parseFloat(montantField.getText());
                    if (currentAccount instanceof Comptepayant) {
                        ((Comptepayant) currentAccount).retirer(montant);
                        resultLabel.setText("Retrait effectué avec succès sur le compte payant.");
                    } else {
                        // For other account types, we'd need to implement the retirer method
                        resultLabel.setText("Opération non supportée pour ce type de compte.");
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Erreur: Veuillez entrer un montant valide.");
                }
            }
        });

        displayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentAccount == null) {
                    resultLabel.setText("Erreur: Veuillez d'abord créer un compte.");
                    return;
                }

                if (currentAccount instanceof Comptesimple) {
                    ((Comptesimple) currentAccount).display();
                    resultLabel.setText("Détails du compte simple: " + currentAccount.toString());
                } else if (currentAccount instanceof Compteepargne) {
                    ((Compteepargne) currentAccount).display();
                    resultLabel.setText("Détails du compte épargne: " + currentAccount.toString());
                } else if (currentAccount instanceof Comptepayant) {
                    ((Comptepayant) currentAccount).display();
                    resultLabel.setText("Détails du compte payant: " + currentAccount.toString());
                }
            }
        });

        calculateInterestBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentAccount == null) {
                    resultLabel.setText("Erreur: Veuillez d'abord créer un compte.");
                    return;
                }

                if (currentAccount instanceof Compteepargne) {
                    ((Compteepargne) currentAccount).calculInteret();
                    resultLabel.setText("Intérêts calculés et ajoutés au compte épargne.");
                } else {
                    resultLabel.setText("Calcul d'intérêt disponible uniquement pour les comptes épargne.");
                }
            }
        });
    }

    public static void main(String[] args) {
        new BAankI();
    }
}