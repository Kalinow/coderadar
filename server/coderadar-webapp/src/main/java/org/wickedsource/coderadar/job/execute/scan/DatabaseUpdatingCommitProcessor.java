package org.wickedsource.coderadar.job.execute.scan;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;
import org.wickedsource.coderadar.commit.domain.Commit;
import org.wickedsource.coderadar.commit.domain.CommitRepository;
import org.wickedsource.coderadar.project.domain.Project;
import org.wickedsource.coderadar.vcs.git.walk.CommitProcessor;

import java.util.Date;

/**
 * Takes a GIT commit and stores it in the database.
 */
class DatabaseUpdatingCommitProcessor implements CommitProcessor {

    private CommitRepository commitRepository;

    private Project project;

    private int updatedCommits;

    DatabaseUpdatingCommitProcessor(CommitRepository commitRepository, Project project) {
        this.commitRepository = commitRepository;
        this.project = project;
    }

    @Override
    public void processCommit(Git gitClient, RevCommit gitCommit) {
        Commit commit = new Commit();
        commit.setName(gitCommit.getName());
        commit.setAuthor(gitCommit.getAuthorIdent().getName());
        commit.setComment(gitCommit.getShortMessage());
        commit.setProject(project);
        commit.setTimestamp(new Date(gitCommit.getCommitTime() * 1000L));
        commitRepository.save(commit);
        updatedCommits++;
    }

    public int getUpdatedCommitsCount(){
        return updatedCommits;
    }

}
