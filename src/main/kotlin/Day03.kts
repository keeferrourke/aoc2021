val r=generateSequence(::readlnOrNull).toList().map{it.map(Char::digitToInt)}
if(args[0]=="1"){val(g,e)=c(r).fold(0 to 0){(g,e),n->((g shl 1)or n)to((e shl 1)+(n xor 1))}
println(g*e)}else println(r(1)*r(0))
fun r(n:Int)={var(s,i)=r to 0
while(s.size>1){val c=c(s)[i]
s=s.filter{it[i]==if(n==1)c xor 1 else c}
i++}
s[0].reduce{a,x->(a shl 1)or x}}()
fun c(r:List<List<Int>>)=r.fold(mutableMapOf<Int,List<Int>>()){a,l->(l.indices).onEach{i->a[i]=a[i]?.plus(l[i])?:listOf(l[i])}
a}.map{(_,v)->v.sortedDescending().groupingBy{it}.eachCount().maxByOrNull{it.value}!!.key}