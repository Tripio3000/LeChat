class MessageDto:

    def __init__(self, message, id, batch_size, num_steps, seed):
        self.id = id
        self.message = message
        self.batch_size = batch_size
        self.num_steps = num_steps
        self.seed = seed

    def get_message(self):
        return self.message

    def get_id(self):
        return self.id

    def get_batch_size(self):
        return self.batch_size

    def get_num_steps(self):
        return self.num_steps

    def get_seed(self):
        return self.seed
