namespace my.bookshop;

entity Books {
	key ID: Integer;
	title: String;
	author: association to Authors;
	stock: Integer;
}

entity Authors {
	key ID: Integer;
	name: String;
	books: association to many Books on books.author = $self;
}

entity Orders {
	key ID: UUID;
	book: association to Books;
	buyer: String;
	date: DateTime;
	amount: Integer;
}