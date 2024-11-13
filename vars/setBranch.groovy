def call(branchName) {
    switch (branchName) {
        case 'dev':
            env.GCDS_Branch = "$BRANCH_NAME"
            break
        case 'Prod':
            env.GCDS_Branch = "$BRANCH_NAME"
            break
        default:
            env.GCDS_Branch = "test"
            break
    }
}