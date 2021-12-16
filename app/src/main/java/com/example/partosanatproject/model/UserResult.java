package com.example.partosanatproject.model;

public class UserResult {
    private String error;
    private String errorCode;
    private UserInfo[] users;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public UserInfo[] getUsers() {
        return users;
    }

    public void setUsers(UserInfo[] users) {
        this.users = users;
    }

    public class UserInfo {
        private String userName;
        private String password;
        private String fullName;
        private String userZipName;
        private String mobileNO;
        private String birthDay;
        private String description;
        private String barcodePrinter;
        private String saveBarcodePrinter;
        private String userLoginKey;
        private ManagerAccess manager;
        private ControlAccess control;
        private ExecutiveAccess executive;
        private FunctionAccess function;
        private CrmAccess crmAccess;
        private boolean editAnotherUserData;
        private boolean cannotChangePassword;
        private boolean disableAccount;
        private boolean disableUSB;
        private boolean postEnabled;
        private int usersGroupID;
        private int userID;
        private int defaultStoreLocationID;
        private int saveDefaultStoreLocationID;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getUserZipName() {
            return userZipName;
        }

        public void setUserZipName(String userZipName) {
            this.userZipName = userZipName;
        }

        public String getMobileNO() {
            return mobileNO;
        }

        public void setMobileNO(String mobileNO) {
            this.mobileNO = mobileNO;
        }

        public String getBirthDay() {
            return birthDay;
        }

        public void setBirthDay(String birthDay) {
            this.birthDay = birthDay;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBarcodePrinter() {
            return barcodePrinter;
        }

        public void setBarcodePrinter(String barcodePrinter) {
            this.barcodePrinter = barcodePrinter;
        }

        public String getSaveBarcodePrinter() {
            return saveBarcodePrinter;
        }

        public void setSaveBarcodePrinter(String saveBarcodePrinter) {
            this.saveBarcodePrinter = saveBarcodePrinter;
        }

        public String getUserLoginKey() {
            return userLoginKey;
        }

        public void setUserLoginKey(String userLoginKey) {
            this.userLoginKey = userLoginKey;
        }

        public ManagerAccess getManager() {
            return manager;
        }

        public void setManager(ManagerAccess manager) {
            this.manager = manager;
        }

        public ControlAccess getControl() {
            return control;
        }

        public void setControl(ControlAccess control) {
            this.control = control;
        }

        public ExecutiveAccess getExecutive() {
            return executive;
        }

        public void setExecutive(ExecutiveAccess executive) {
            this.executive = executive;
        }

        public FunctionAccess getFunction() {
            return function;
        }

        public void setFunction(FunctionAccess function) {
            this.function = function;
        }

        public CrmAccess getCrmAccess() {
            return crmAccess;
        }

        public void setCrmAccess(CrmAccess crmAccess) {
            this.crmAccess = crmAccess;
        }

        public boolean isEditAnotherUserData() {
            return editAnotherUserData;
        }

        public void setEditAnotherUserData(boolean editAnotherUserData) {
            this.editAnotherUserData = editAnotherUserData;
        }

        public boolean isCannotChangePassword() {
            return cannotChangePassword;
        }

        public void setCannotChangePassword(boolean cannotChangePassword) {
            this.cannotChangePassword = cannotChangePassword;
        }

        public boolean isDisableAccount() {
            return disableAccount;
        }

        public void setDisableAccount(boolean disableAccount) {
            this.disableAccount = disableAccount;
        }

        public boolean isDisableUSB() {
            return disableUSB;
        }

        public void setDisableUSB(boolean disableUSB) {
            this.disableUSB = disableUSB;
        }

        public boolean isPostEnabled() {
            return postEnabled;
        }

        public void setPostEnabled(boolean postEnabled) {
            this.postEnabled = postEnabled;
        }

        public int getUsersGroupID() {
            return usersGroupID;
        }

        public void setUsersGroupID(int usersGroupID) {
            this.usersGroupID = usersGroupID;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public int getDefaultStoreLocationID() {
            return defaultStoreLocationID;
        }

        public void setDefaultStoreLocationID(int defaultStoreLocationID) {
            this.defaultStoreLocationID = defaultStoreLocationID;
        }

        public int getSaveDefaultStoreLocationID() {
            return saveDefaultStoreLocationID;
        }

        public void setSaveDefaultStoreLocationID(int saveDefaultStoreLocationID) {
            this.saveDefaultStoreLocationID = saveDefaultStoreLocationID;
        }
    }

    public class ManagerAccess {
        private boolean managerExternalOrder;
        private boolean managerOrderOK;
        private boolean managerShipping;
        private boolean managerInternalOrder;
        private boolean managerBoardOrder;
        private boolean managerSKD;
        private boolean manager3R;
        private boolean managerStore;
        private boolean managerQC;
        private boolean managerAssemble;
        private boolean managerMRP;
        private boolean managerRepairInstall;
        private boolean managerPRD;
        private boolean accessSalesProductionReport;

        public boolean isManagerExternalOrder() {
            return managerExternalOrder;
        }

        public void setManagerExternalOrder(boolean managerExternalOrder) {
            this.managerExternalOrder = managerExternalOrder;
        }

        public boolean isManagerOrderOK() {
            return managerOrderOK;
        }

        public void setManagerOrderOK(boolean managerOrderOK) {
            this.managerOrderOK = managerOrderOK;
        }

        public boolean isManagerShipping() {
            return managerShipping;
        }

        public void setManagerShipping(boolean managerShipping) {
            this.managerShipping = managerShipping;
        }

        public boolean isManagerInternalOrder() {
            return managerInternalOrder;
        }

        public void setManagerInternalOrder(boolean managerInternalOrder) {
            this.managerInternalOrder = managerInternalOrder;
        }

        public boolean isManagerBoardOrder() {
            return managerBoardOrder;
        }

        public void setManagerBoardOrder(boolean managerBoardOrder) {
            this.managerBoardOrder = managerBoardOrder;
        }

        public boolean isManagerSKD() {
            return managerSKD;
        }

        public void setManagerSKD(boolean managerSKD) {
            this.managerSKD = managerSKD;
        }

        public boolean isManager3R() {
            return manager3R;
        }

        public void setManager3R(boolean manager3R) {
            this.manager3R = manager3R;
        }

        public boolean isManagerStore() {
            return managerStore;
        }

        public void setManagerStore(boolean managerStore) {
            this.managerStore = managerStore;
        }

        public boolean isManagerQC() {
            return managerQC;
        }

        public void setManagerQC(boolean managerQC) {
            this.managerQC = managerQC;
        }

        public boolean isManagerAssemble() {
            return managerAssemble;
        }

        public void setManagerAssemble(boolean managerAssemble) {
            this.managerAssemble = managerAssemble;
        }

        public boolean isManagerMRP() {
            return managerMRP;
        }

        public void setManagerMRP(boolean managerMRP) {
            this.managerMRP = managerMRP;
        }

        public boolean isManagerRepairInstall() {
            return managerRepairInstall;
        }

        public void setManagerRepairInstall(boolean managerRepairInstall) {
            this.managerRepairInstall = managerRepairInstall;
        }

        public boolean isManagerPRD() {
            return managerPRD;
        }

        public void setManagerPRD(boolean managerPRD) {
            this.managerPRD = managerPRD;
        }

        public boolean isAccessSalesProductionReport() {
            return accessSalesProductionReport;
        }

        public void setAccessSalesProductionReport(boolean accessSalesProductionReport) {
            this.accessSalesProductionReport = accessSalesProductionReport;
        }
    }

    public class ControlAccess {
        private boolean controlOrder;
        private boolean controlBoard;
        private boolean controlSKD;
        private boolean controlStore;
        private boolean controlQc;
        private boolean controlAssemble;
        private boolean controlRepair;

        public boolean isControlOrder() {
            return controlOrder;
        }

        public void setControlOrder(boolean controlOrder) {
            this.controlOrder = controlOrder;
        }

        public boolean isControlBoard() {
            return controlBoard;
        }

        public void setControlBoard(boolean controlBoard) {
            this.controlBoard = controlBoard;
        }

        public boolean isControlSKD() {
            return controlSKD;
        }

        public void setControlSKD(boolean controlSKD) {
            this.controlSKD = controlSKD;
        }

        public boolean isControlStore() {
            return controlStore;
        }

        public void setControlStore(boolean controlStore) {
            this.controlStore = controlStore;
        }

        public boolean isControlQc() {
            return controlQc;
        }

        public void setControlQc(boolean controlQc) {
            this.controlQc = controlQc;
        }

        public boolean isControlAssemble() {
            return controlAssemble;
        }

        public void setControlAssemble(boolean controlAssemble) {
            this.controlAssemble = controlAssemble;
        }

        public boolean isControlRepair() {
            return controlRepair;
        }

        public void setControlRepair(boolean controlRepair) {
            this.controlRepair = controlRepair;
        }
    }

    public class ExecutiveAccess {
        private boolean executiveStore;
        private boolean executiveQC;
        private boolean executiveAssemble;
        private boolean executivePost;
        private boolean executiveRework;
        private boolean executiveRepairInstall;

        public boolean isExecutiveStore() {
            return executiveStore;
        }

        public void setExecutiveStore(boolean executiveStore) {
            this.executiveStore = executiveStore;
        }

        public boolean isExecutiveQC() {
            return executiveQC;
        }

        public void setExecutiveQC(boolean executiveQC) {
            this.executiveQC = executiveQC;
        }

        public boolean isExecutiveAssemble() {
            return executiveAssemble;
        }

        public void setExecutiveAssemble(boolean executiveAssemble) {
            this.executiveAssemble = executiveAssemble;
        }

        public boolean isExecutivePost() {
            return executivePost;
        }

        public void setExecutivePost(boolean executivePost) {
            this.executivePost = executivePost;
        }

        public boolean isExecutiveRework() {
            return executiveRework;
        }

        public void setExecutiveRework(boolean executiveRework) {
            this.executiveRework = executiveRework;
        }

        public boolean isExecutiveRepairInstall() {
            return executiveRepairInstall;
        }

        public void setExecutiveRepairInstall(boolean executiveRepairInstall) {
            this.executiveRepairInstall = executiveRepairInstall;
        }
    }

    public class FunctionAccess {
        private boolean accessDocuments;
        private boolean accessSystemSetting;
        private boolean accessUsersPermission;
        private boolean accessUpdate;
        private boolean accessSendFile;
        private boolean showPartoNoPrice;
        private boolean accessPartoNo;
        private boolean accessStoreLocation;
        private boolean accessPowers;
        private boolean accessPlans;
        private boolean accessProjects;
        private boolean accessCurrency;

        public boolean isAccessDocuments() {
            return accessDocuments;
        }

        public void setAccessDocuments(boolean accessDocuments) {
            this.accessDocuments = accessDocuments;
        }

        public boolean isAccessSystemSetting() {
            return accessSystemSetting;
        }

        public void setAccessSystemSetting(boolean accessSystemSetting) {
            this.accessSystemSetting = accessSystemSetting;
        }

        public boolean isAccessUsersPermission() {
            return accessUsersPermission;
        }

        public void setAccessUsersPermission(boolean accessUsersPermission) {
            this.accessUsersPermission = accessUsersPermission;
        }

        public boolean isAccessUpdate() {
            return accessUpdate;
        }

        public void setAccessUpdate(boolean accessUpdate) {
            this.accessUpdate = accessUpdate;
        }

        public boolean isAccessSendFile() {
            return accessSendFile;
        }

        public void setAccessSendFile(boolean accessSendFile) {
            this.accessSendFile = accessSendFile;
        }

        public boolean isShowPartoNoPrice() {
            return showPartoNoPrice;
        }

        public void setShowPartoNoPrice(boolean showPartoNoPrice) {
            this.showPartoNoPrice = showPartoNoPrice;
        }

        public boolean isAccessPartoNo() {
            return accessPartoNo;
        }

        public void setAccessPartoNo(boolean accessPartoNo) {
            this.accessPartoNo = accessPartoNo;
        }

        public boolean isAccessStoreLocation() {
            return accessStoreLocation;
        }

        public void setAccessStoreLocation(boolean accessStoreLocation) {
            this.accessStoreLocation = accessStoreLocation;
        }

        public boolean isAccessPowers() {
            return accessPowers;
        }

        public void setAccessPowers(boolean accessPowers) {
            this.accessPowers = accessPowers;
        }

        public boolean isAccessPlans() {
            return accessPlans;
        }

        public void setAccessPlans(boolean accessPlans) {
            this.accessPlans = accessPlans;
        }

        public boolean isAccessProjects() {
            return accessProjects;
        }

        public void setAccessProjects(boolean accessProjects) {
            this.accessProjects = accessProjects;
        }

        public boolean isAccessCurrency() {
            return accessCurrency;
        }

        public void setAccessCurrency(boolean accessCurrency) {
            this.accessCurrency = accessCurrency;
        }
    }

    public class CrmAccess {
        private boolean accessCustomers;
        private boolean accessCustomersManager;
        private boolean accessProforma;
        private boolean accessProformaManager;
        private int accessContract;
        private boolean accessContractManager;
        private boolean accessFinance;
        private boolean accessFinanceManager;

        public boolean isAccessCustomers() {
            return accessCustomers;
        }

        public void setAccessCustomers(boolean accessCustomers) {
            this.accessCustomers = accessCustomers;
        }

        public boolean isAccessCustomersManager() {
            return accessCustomersManager;
        }

        public void setAccessCustomersManager(boolean accessCustomersManager) {
            this.accessCustomersManager = accessCustomersManager;
        }

        public boolean isAccessProforma() {
            return accessProforma;
        }

        public void setAccessProforma(boolean accessProforma) {
            this.accessProforma = accessProforma;
        }

        public boolean isAccessProformaManager() {
            return accessProformaManager;
        }

        public void setAccessProformaManager(boolean accessProformaManager) {
            this.accessProformaManager = accessProformaManager;
        }

        public int getAccessContract() {
            return accessContract;
        }

        public void setAccessContract(int accessContract) {
            this.accessContract = accessContract;
        }

        public boolean isAccessContractManager() {
            return accessContractManager;
        }

        public void setAccessContractManager(boolean accessContractManager) {
            this.accessContractManager = accessContractManager;
        }

        public boolean isAccessFinance() {
            return accessFinance;
        }

        public void setAccessFinance(boolean accessFinance) {
            this.accessFinance = accessFinance;
        }

        public boolean isAccessFinanceManager() {
            return accessFinanceManager;
        }

        public void setAccessFinanceManager(boolean accessFinanceManager) {
            this.accessFinanceManager = accessFinanceManager;
        }
    }

    public class UserLoginParameter {
        private String userName;
        private String password;
        private int version;

        public UserLoginParameter(String userName, String password, int version) {
            this.userName = userName;
            this.password = password;
            this.version = version;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
