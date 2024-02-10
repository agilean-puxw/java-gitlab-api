package org.gitlab.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class GitlabCommitDiff {

    public final static String URL = "/diff";

    @JsonProperty("diff")
    private String diff;

    @JsonProperty("new_path")
    private String newPath;

    @JsonProperty("old_path")
    private String oldPath;

    @JsonProperty("a_mode")
    private String aMode;

    @JsonProperty("b_mode")
    private String bMode;

    @JsonProperty("new_file")
    private boolean newFile;

    @JsonProperty("renamed_file")
    private boolean renamedFile;

    private int insertion = -1;
    private int deletion = -1;

    @JsonProperty("deleted_file")
    private boolean deletedFile;

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getNewPath() {
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public String getAMode() {
        return aMode;
    }

    public void setAMode(String aMode) {
        this.aMode = aMode;
    }

    public String getBMode() {
        return bMode;
    }

    public void setBMode(String bMode) {
        this.bMode = bMode;
    }

    public boolean getNewFile() {
        return newFile;
    }

    public void setNewFile(boolean newFile) {
        this.newFile = newFile;
    }

    public boolean getRenamedFile() {
        return renamedFile;
    }

    public void setRenamedFile(boolean renamedFile) {
        this.renamedFile = renamedFile;
    }

    public boolean getDeletedFile() {
        return deletedFile;
    }

    public void setDeletedFile(boolean deletedFile) {
        this.deletedFile = deletedFile;
    }

    public int getInsertion() {
        if (renamedFile) {
            insertion = 0;
            deletion = 0;
            return insertion;
        }
        return doLatest();
    }
    private int doLatest() {
        if (insertion == -1) {
            insertion = 0;
            deletion = 0;
            if (diff != null && !"".equals(diff)) {
                String[] lines = diff.split("\n");
                for (String line : lines) {
                    if (line.startsWith("+")) {
                        insertion++;
                    } else if (line.startsWith("-")) {
                        deletion++;
                    }
                }
            }
        }
        return insertion;
    }
    public int getDeletion() {
        if (deletion == -1) {
            getInsertion();
        }
        return deletion;
    }
}
