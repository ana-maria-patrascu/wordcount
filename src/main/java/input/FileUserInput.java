package input;

import java.util.List;

class FileUserInput extends UsersInput {
    public FileUserInput(String from) {
        super.from = from;
    }

    public List<String> getInput() {
        return InputUtils.readFromFile(super.from);
    }
}
