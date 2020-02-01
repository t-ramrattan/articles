## Running MongoDB locally
> ./mongod --config ~/config/mongod.conf;

## Paginate 
Idea have a unique numeric identifier. All articles are inserted sequentially 
> db.inventory.find({qty:{$gt: 75}}).sort({qty:1}).limit(2);

## random article generator 

https://picsum.photos/498/280
