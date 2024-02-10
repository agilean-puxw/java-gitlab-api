package org.gitlab.api;

import org.gitlab.api.models.GitlabCommit;
import org.gitlab.api.models.GitlabCommitDiff;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GitlabCommitIT {


    static GitlabAPI api;

    @BeforeClass
    public static void before() {
        String TEST_URL = "https://gitlab.com";
        api = GitlabAPI.connect(TEST_URL, "glpat-og4ikL2fE8svxJcBsKG2");
    }

    @Test
    public void diff() throws IOException {
        List<GitlabCommit> main = api.getCommits("54588717", LocalDateTime.of(2023, 01, 01, 0, 0, 0), null, "main");
        assertNotNull(main);
        main.stream().forEach(c -> System.out.println("id: " + c.getId()));
        List<GitlabCommitDiff> commitDiffs = api.getCommitDiffs("54588717", "6004a78a2e0412fde6df6c5d89d1a2760408709b");
        assertEquals(93, commitDiffs.get(0).getInsertion());
        assertEquals(0, commitDiffs.get(0).getDeletion());
    }
}
