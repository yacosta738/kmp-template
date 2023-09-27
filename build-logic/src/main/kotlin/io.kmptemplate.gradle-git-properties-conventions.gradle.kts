
plugins{
    id("com.gorylenko.gradle-git-properties")
}

gitProperties {
    failOnNoGitDirectory = false
    keys = listOf("git.branch", "git.commit.id.abbrev", "git.commit.id.describe")
}
