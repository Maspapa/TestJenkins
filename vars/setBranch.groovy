def call(branchName) {
    switch (branchName) {
        case 'dev':
            env.GCDS_Branch = branchName
            break
        case 'Prod':
            env.GCDS_Branch = branchName
            break
        default:
            env.GCDS_Branch = "test"
            break
    }
}