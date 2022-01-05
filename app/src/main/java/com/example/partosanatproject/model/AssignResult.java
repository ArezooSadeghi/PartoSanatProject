package com.example.partosanatproject.model;

public class AssignResult extends ResultInfo {
    private AssignInfo[] assigns;

    public AssignInfo[] getAssigns() {
        return assigns;
    }

    public void setAssigns(AssignInfo[] assigns) {
        this.assigns = assigns;
    }

    public class AssignInfo {
        private int assignID;
        private int caseID;
        private int caseProductID;
        private int assignUserID;
        private int parentID;
        private int userID;
        private long addTime;
        private long seenTime;
        private long finishTime;
        private boolean finish;
        private boolean seen;
        private boolean SeenAssigner;
        private String description;
        private String userFullName;
        private String assignUserFullName;

        public int getAssignID() {
            return assignID;
        }

        public void setAssignID(int assignID) {
            this.assignID = assignID;
        }

        public int getCaseID() {
            return caseID;
        }

        public void setCaseID(int caseID) {
            this.caseID = caseID;
        }

        public int getCaseProductID() {
            return caseProductID;
        }

        public void setCaseProductID(int caseProductID) {
            this.caseProductID = caseProductID;
        }

        public int getAssignUserID() {
            return assignUserID;
        }

        public void setAssignUserID(int assignUserID) {
            this.assignUserID = assignUserID;
        }

        public int getParentID() {
            return parentID;
        }

        public void setParentID(int parentID) {
            this.parentID = parentID;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public long getSeenTime() {
            return seenTime;
        }

        public void setSeenTime(long seenTime) {
            this.seenTime = seenTime;
        }

        public long getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(long finishTime) {
            this.finishTime = finishTime;
        }

        public boolean isFinish() {
            return finish;
        }

        public void setFinish(boolean finish) {
            this.finish = finish;
        }

        public boolean isSeen() {
            return seen;
        }

        public void setSeen(boolean seen) {
            this.seen = seen;
        }

        public boolean isSeenAssigner() {
            return SeenAssigner;
        }

        public void setSeenAssigner(boolean seenAssigner) {
            SeenAssigner = seenAssigner;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUserFullName() {
            return userFullName;
        }

        public void setUserFullName(String userFullName) {
            this.userFullName = userFullName;
        }

        public String getAssignUserFullName() {
            return assignUserFullName;
        }

        public void setAssignUserFullName(String assignUserFullName) {
            this.assignUserFullName = assignUserFullName;
        }
    }

    public class SeenParameter {
        private int assignID;

        public int getAssignID() {
            return assignID;
        }

        public void setAssignID(int assignID) {
            this.assignID = assignID;
        }
    }
}
