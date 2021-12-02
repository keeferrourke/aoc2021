data class A<T>(val x:T,val y:T,val a:T)
val p=args[0]=="1"
println(generateSequence(::readlnOrNull).map{val(a,b)=it.split(" ")
a to b.toInt()}.fold(A(0,0,0)){a,(c,m)->when(c[0]){
'f'->if(p)a.copy(a.x+m)else a.copy(a.x+m,a.y+m*a.a)
'u'->if(p)a.copy(y=a.y-m)else a.copy(a=a.a-m)
else->if(p)a.copy(y=a.y+m)else a.copy(a=a.a+m)
}}.run{x*y})