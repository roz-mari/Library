UPDATE `library`.`books`
SET
`id` = <{id: }>,
`title` = <{title: }>,
`authors` = <{authors: }>,
`isbn` = <{isbn: }>,
`genres` = <{genres: }>,
`description` = <{description: }>
WHERE `id` = <{expr}>;
